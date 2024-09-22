
// Classe base
class Pessoa(val name: String, val age: Int) {
  def hello(): Unit = {
    println(s"Hello, meu nome é $name e eu tenho $age anos de idade.")
  }

  def introducao(): Unit = {
    println(s"Eu sou uma pessoa.")
  }
}

// Subclasse Estudante
class Estudante(name: String, age: Int, val EstudanteId: String) extends Pessoa(name, age) {
  override def hello(): Unit = {
    println(s"Hello, meu nome é $name, eu tenho $age anos de idade e meu ID de Estudante é $EstudanteId.")
  }

  def estudanteIntroducao(): Unit = {
    introducao()
    println(s"E tenho status de estudante.")
  }
}

// Subclasse Professor
class Professor(name: String, age: Int, val subject: String) extends Pessoa(name, age) {
  override def hello(): Unit = {
    println(s"Hello, meu nome é $name, eu tenho $age anos de idade e ensino $subject.")
  }

  def professorIntroducao(): Unit = {
    introducao()
    println(s"E tenho status de professor.")
  }
}

// Cria o objeto
object ClassesAndObjects {
  
  // Função main
  def main(args: Array[String]): Unit = {
    
    // Criando instâncias de classes
    val Pessoa1 = new Pessoa("Alice", 30)
    val Estudante1 = new Estudante("Bob", 25, "S12345")
    val Professor1 = new Professor("Carol", 40, "Matemática")
    println()

    // Chamando métodos
    Pessoa1.hello()
    Pessoa1.introducao()
    println()

    // Chamando introdução nas subclasses
    Estudante1.hello()
    Estudante1.estudanteIntroducao()
    println()
    Professor1.hello()
    Professor1.professorIntroducao()
    println()

    // Polimorfismo
    // A lista "people" demonstra o polimorfismo, onde diferentes subclasses são tratadas 
    // como instâncias da classe base Pessoa e o método hello correto é chamado para cada instância.
    val people: List[Pessoa] = List(Pessoa1, Estudante1, Professor1)
    people.foreach(_.hello())
    println()
  }
}
