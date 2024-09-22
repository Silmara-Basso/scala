
// Lab - Processo ETL em Banco de Dados Relacional com Linguagem Scala

import com.github.tototoshi.csv._
import java.sql.{Connection, DriverManager, ResultSet}

// Cria o objeto
object CsvToSQLite {

  // Função main
  def main(args: Array[String]): Unit = {

    // Caminho para o arquivo CSV
    val csvFilePath = "/scripts/etl-pipeline/dataset.csv"
    
    // Cria uma conexão com o banco de dados SQLite (o banco de dados se não existir)
    val url = "jdbc:sqlite:mkt.db"
    val conn = DriverManager.getConnection(url)
    
    // Leitura do arquivo CSV
    val reader = CSVReader.open(new java.io.File(csvFilePath))
    val allRows = reader.allWithHeaders()
    
    // Remove espaços em branco dos nomes das colunas e ajustando o dataset com cabeçalho sem espaço no nome e as linhas
    val cleanedHeaders = allRows.head.keys.map(_.trim)
    val cleanedRows = allRows.map(row => row.map { case (k, v) => (k.trim, v) })

    // Verifique se as colunas que iremos processar estão presentes ("ID", "Ano_Nascimento", "Renda", "Estado_Civil")
    val expectedColumns = List("ID", "Ano_Nascimento", "Renda", "Estado_Civil")
    val missingColumns = expectedColumns.filterNot(cleanedHeaders.toSeq.contains)
      
    // Se uma das colunas que iremos processar não existir, emitimos mensagem de erro
    if (missingColumns.nonEmpty) {
      println(s"Erro: Columns not found: ${missingColumns.mkString(", ")}")
      println("Colunas disponíveis no CSV:")
      cleanedHeaders.foreach(println)
      sys.exit(1)
    }

    // Bloco try/finally
    try {

      // Cria uma tabela para armazenar os dados (deletando antes se já existir)
      val statement = conn.createStatement()
      statement.executeUpdate("DROP TABLE IF EXISTS tb_mkt")
      statement.executeUpdate("""
        CREATE TABLE tb_mkt (
          ID INTEGER,
          Ano_Nascimento INTEGER,
          Renda TEXT,
          Estado_Civil_Casado INTEGER,
          Estado_Civil_Solteiro INTEGER,
          Estado_Civil_Viúvo INTEGER,
          Estado_Civil_Outro INTEGER
        )
      """)

      // Map para Limpeza e transformação de dados
      val transformedRows = cleanedRows.map { row =>
        
        // Ajusta o tipo das variáveis numéricas
        val id = row("ID").toIntOption.getOrElse(0)
        val anoNascimento = row("Ano_Nascimento").toIntOption.getOrElse(0)

        // Remove espaços
        val renda = row("Renda").trim

        // Encoding da variável categórica
        val estadoCivil = row("Estado_Civil")
        val estadoCivilCasado = if (estadoCivil == "Casado") 1 else 0
        val estadoCivilSolteiro = if (estadoCivil == "Solteiro") 1 else 0
        val estadoCivilViuvo = if (estadoCivil == "Viúvo") 1 else 0
        val estadoCivilOutro = if (estadoCivil != "Casado" && estadoCivil != "Solteiro" && estadoCivil != "Viúvo") 1 else 0

        (id, anoNascimento, renda, estadoCivilCasado, estadoCivilSolteiro, estadoCivilViuvo, estadoCivilOutro)
      }

      // Insere os dados limpos e processados no banco de dados
      val preparedStatement = conn.prepareStatement("INSERT INTO tb_mkt (ID, Ano_Nascimento, Renda, Estado_Civil_Casado, Estado_Civil_Solteiro, Estado_Civil_Viúvo, Estado_Civil_Outro) VALUES (?, ?, ?, ?, ?, ?, ?)")
      transformedRows.foreach { case (id, anoNascimento, renda, estadoCivilCasado, estadoCivilSolteiro, estadoCivilViuvo, estadoCivilOutro) =>
        preparedStatement.setInt(1, id)
        preparedStatement.setInt(2, anoNascimento)
        preparedStatement.setString(3, renda)
        preparedStatement.setInt(4, estadoCivilCasado)
        preparedStatement.setInt(5, estadoCivilSolteiro)
        preparedStatement.setInt(6, estadoCivilViuvo)
        preparedStatement.setInt(7, estadoCivilOutro)
        preparedStatement.addBatch()
      }
      preparedStatement.executeBatch()

      println("Dados inseridos com sucesso no banco de dados SQLite.")

    } finally {

      // Fecha a conexão com o banco de dados
      conn.close()
    }
  }
}

// O código utiliza a classe implícita StringOps para converter strings em valores Option, o que melhora a robustez e segurança do código 
// ao lidar com possíveis entradas inválidas.

// Definindo uma classe implícita para adicionar métodos de conversão a String
implicit class StringOps(s: String) {
  
  // Método para converter a string para uma opção de inteiro
  def toIntOption: Option[Int] = try {
    // Tentando converter a string para inteiro e retornando como Some
    Some(s.toInt)
  } catch {
    // Caso ocorra uma exceção de formato inválido, retorna None
    case _: NumberFormatException => None
  }

  // Método para converter a string para uma opção de double
  def toDoubleOption: Option[Double] = try {
    // Tentando converter a string para double e retornando como Some
    Some(s.toDouble)
  } catch {
    // Caso ocorra uma exceção de formato inválido, retorna None
    case _: NumberFormatException => None
  }
}

