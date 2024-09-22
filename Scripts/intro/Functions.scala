
object Functions {

  // Cria a função principal
  def main(args: Array[String]): Unit = {

    // Função 1
    // Função para soma dos valores
    def somaValores(a: Int, b: Int): Int = {
      a + b
    }

    // Função 2
    // Em Scala, uma função que retorna Unit é uma função que não retorna um valor significativo. 
    // O tipo Unit é similar ao void em outras linguagens de programação como Java e C#. 
    // Quando uma função tem um retorno de tipo Unit, isso significa que o objetivo principal da função 
    // é executar alguma ação, como imprimir algo na tela ou modificar o estado de um objeto, 
    // em vez de calcular e retornar um valor.
    def hello(nome: String): Unit = {
      println(s"Hello, $nome!")
    }

    // Função 3
    // Função anônima (similar à expressão lambda em Python)
    val multiplicaValores = (x: Int, y: Int) => x * y

    // Chamando funções

    // Função 1
    val soma = somaValores(3, 5)
    println(s"Soma: $soma")
    
    // Função 2
    hello("Scala")

    // Função 3
    val resultado = multiplicaValores(4, 7)
    println(s"Produto: $resultado")
  }
}
