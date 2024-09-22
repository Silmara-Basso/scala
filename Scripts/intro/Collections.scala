
object Collections {

  // Função main
  def main(args: Array[String]): Unit = {
    
    // Listas
    val numbers = List(1, 2, 3, 4, 5, 6)
    println(s"Números: $numbers")

    // Maps (Dicionários)
    val nameAgeMap = Map("Alice" -> 30, "Bob" -> 25)
    println(s"Map Nome-Idade: $nameAgeMap")

    // Conjuntos
    val uniqueNumbers = Set(1, 2, 2, 3, 3, 3, 4, 4)
    println(s"Números Únicos: $uniqueNumbers")

    // Operações em coleções
    val doubledNumbers = numbers.map(_ * 2)
    println(s"Números Double: $doubledNumbers")

    // Operações em coleções
    val filteredNumbers = numbers.filter(_ > 3)
    println(s"Filtrando números maiores que 3: $filteredNumbers")

    // Filtra os números pares e depois multiplica cada um por 2
    val doubledEvenNumbers = numbers.filter(_ % 2 == 0).map(_ * 2)
    println(s"Números Pares Dobrados: $doubledEvenNumbers")

    // Reduce
    val soma = List(1, 2, 3, 4).reduce(_ + _) 
    println(s"Soma dos Elementos da Lista: $soma")

    // flatMap
    val letras = List("Hello", "World").flatMap(_.split("")) 
    println(s"Letras: $letras")

    // Array
    val array = Array(1, 2, 3, 4, 5)

    // Combina cada elemento do array com seu índice
    val arrayWithIndices = array.zipWithIndex

    // Exibe os elementos junto com seus índices
    arrayWithIndices.foreach { case (element, index) =>
      println(s"Elemento: $element, Índice: $index")
    }
    
  }
}
