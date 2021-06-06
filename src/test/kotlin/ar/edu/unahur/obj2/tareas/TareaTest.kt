package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class TareaTest : DescribeSpec({
  describe("Una tarea") {
    val empleado20p = Empleado(20.0)
    val empleado30p = Empleado(30.0)
    val empleado40p = Empleado(40.0)
    val empleado50p = Empleado(50.0)
    val empleado60p = Empleado(60.0)

    val responsable100 = Responsable(100.0, mutableListOf(empleado20p, empleado30p, empleado40p, empleado50p, empleado60p))

    val tareaRandom = Tarea(50, 1000.0, responsable100)

    describe("probando una tarea") {

      it("probando costo por empleado") {
        tareaRandom.costoPorEmpleados() shouldBe mutableListOf(200, 300, 400, 500, 600)
        tareaRandom.costoTotalDeEmpleados() shouldBe 2000
        tareaRandom.costoTotalTarea() shouldBe 8000
      }
    }
    describe("Req 1: nomina de empleados para una tarea") {
      tareaRandom.nominaDeEmpleados().shouldContainExactlyInAnyOrder(
        mutableListOf(empleado20p, empleado30p, empleado40p, empleado50p, empleado60p, responsable100))
    }
  }
})
