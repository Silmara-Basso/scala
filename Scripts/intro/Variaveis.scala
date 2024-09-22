
object VariablesAndTypes {

  // Função main
  def main(args: Array[String]): Unit = {
    
    // Variáveis mutáveis e imutáveis
    var mutableVar: Int = 10
    val immutableVal: String = "Hello, Scala"

    // Tipos básicos
    val intVal: Int = 42
    val doubleVal: Double = 3.14
    val stringVal: String = "Aprendendo Scala!"

    // Exibindo valores
    println(s"Variável Mutável: $mutableVar")
    println(s"Variável Imutável: $immutableVal")
    println(s"Valor Inteiro: $intVal")
    println(s"Valor Double: $doubleVal")
    println(s"Valor String: $stringVal")

    // Modificando a variável mutável
    mutableVar = 20
    println(s"Variável Mutável Modificada: $mutableVar")

    // Tentando modificar a variável immutável
    // val intVal: Int = 100
    // println(s"Valor Inteiro: $intVal")

    // Agora é variável mutável
    //var intVal2: Int = 100
    //println(s"Valor Inteiro: $intVal2")

  }
}
