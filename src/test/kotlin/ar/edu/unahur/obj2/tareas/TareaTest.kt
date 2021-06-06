package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  describe("Una tarea") {
    val empleado20p = Empleados(20)
    val empleado30p = Empleados(30)
    val empleado40p = Empleados(40)
    val empleado50p = Empleados(50)
    val empleado60p = Empleados(60)

    val responsable100 = Responsable(mutableListOf(empleado20p, empleado30p, empleado40p, empleado50p, empleado60p), 100)

    val tareaRandom = Tarea(50, 1000, responsable100)

    describe("probando una tarea") {

      it("probando costo por empleado") {
        tareaRandom.costoPorEmpleados() shouldBe mutableListOf(200, 300, 400, 500, 600)
        tareaRandom.costoTotalDeEmpleados() shouldBe 2000
        tareaRandom.costoTotalInfraestructura() shouldBe 8000
      }
    }
  }
})
