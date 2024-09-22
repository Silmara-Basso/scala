// Pattern Matching

object PatternMatching {

  // Cria a função
  def main(args: Array[String]): Unit = {

    // Função com correspondência de padrões
    // O tipo Any é o supertipo de todos os outros tipos em Scala, então x pode ser qualquer valor.
    def describe(x: Any): String = x match {

      case 1 => "Um"
      case "hello" => "Saudação"
      case true => "Verdadeiro"
      case Nil => "Lista vazia"

      // O caracter _ é um caracter especial que corresponde a qualquer valor não coberto pelos casos anteriores. 
      // Se nenhum dos casos anteriores corresponder ao valor de x, a função retorna a string "Qualquer outra coisa".
      case _ => "Qualquer outra coisa"
    }

    // Testando a função
    println(describe(1))
    println(describe("hello"))
    println(describe("hello world"))
    println(describe(true))
    println(describe(List()))
    println(describe(42))
  }
}
