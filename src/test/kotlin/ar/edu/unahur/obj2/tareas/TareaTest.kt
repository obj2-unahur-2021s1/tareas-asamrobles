package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class TareaTest : DescribeSpec({
  describe("Una tarea") {
    //empleados Responsable100
    val empleado20p = EmpleadoComun(20.0)
    val empleado30p = EmpleadoComun(30.0)
    val empleado40p = EmpleadoComun(40.0)
    val empleado50p = EmpleadoComun(50.0)
    val empleado60p = EmpleadoComun(60.0)

    //empleados Responsable150
    val empleado70p = EmpleadoComun(70.0)
    val empleado80p = EmpleadoComun(80.0)
    val empleado90p = EmpleadoComun(90.0)
    val empleado100p = EmpleadoComun(100.0)

    //empleados Responsable200
    val empleado120p = EmpleadoComun(120.0)
    val empleado130p = EmpleadoComun(130.0)
    val empleado140p = EmpleadoComun(140.0)


    //responsables
    val responsable100 = Responsable(100.0, mutableListOf(empleado20p, empleado30p, empleado40p, empleado50p, empleado60p))
    val responsable150 = Responsable(150.0, mutableListOf(empleado70p, empleado80p, empleado90p, empleado100p))
    val responsable200 = Responsable(200.0, mutableListOf(empleado120p, empleado130p, empleado140p))

    //tareas comunes
    val tareaSimple1 = Tarea(50, 1000.0, responsable100)
    val tareaSimple2 = Tarea(80, 1500.0, responsable150)
    val tareaSimple3 = Tarea(90, 2000.0, responsable200)

    describe("probando una tarea") {

      it("probando costo por empleado") {
        tareaSimple1.costoPorEmpleados() shouldBe mutableListOf(200, 300, 400, 500, 600)
        tareaSimple1.costoTotalDeEmpleados() shouldBe 2000
        tareaSimple1.costoTotalTarea() shouldBe 8000
      }
    }
    describe("Req 1: nomina de empleados para una tarea") {

      it("nomina de empleados de tareaSimple1") {
        tareaSimple1.nominaDeEmpleados().shouldContainExactlyInAnyOrder(
          mutableListOf(empleado20p, empleado30p, empleado40p, empleado50p, empleado60p, responsable100))
      }
      it("nomina de empleados de tareaSimple2") {
        tareaSimple2.nominaDeEmpleados().shouldContainExactlyInAnyOrder(
          mutableListOf(empleado70p, empleado80p, empleado90p, empleado100p, responsable150))
      }
      it("nomina de empleados de tareaSimple3") {
        tareaSimple3.nominaDeEmpleados().shouldContainExactlyInAnyOrder(
          mutableListOf(empleado120p, empleado130p, empleado140p, responsable200))
      }
    }
    describe("Req 2: horas necesarias para finalizar una tarea comun") {

      it("una tarea que tiene un tiempo estimado de 50 horas y 5 empleados debe finalizar en 10 horas") {
        tareaSimple1.horasNecesariasParaFinalizar() shouldBe 10
      }
      it("una tarea que tiene un tiempo estimado de 80 horas y 4 empleados debe finalizar en 20 horas") {
        tareaSimple2.horasNecesariasParaFinalizar() shouldBe 20
      }
      it("una tarea que tiene un tiempo estimado de 90 horas y 3 empleados debe finalizar en 30 horas") {
        tareaSimple3.horasNecesariasParaFinalizar() shouldBe 30
      }
    }
    describe("Req: 3 costo de una tarea con 1 responsable y varios empleados") {

      it("una tarea que lleva 10 horas finalizar, con 5 empleados con sueldos de 20, 30, 40, 50, 60 y un costo de infraestructura de 1000 debe tener un costo final de 8000") {
        tareaSimple1.costoTotalTarea() shouldBe 8000
      }
      it("una tarea que lleva 20 horas finalizar, con 4 empleados, con sueldos de 70, 80, 90, 100 y un costo de infraestructura de 1500 debe tener un costo final de 20300") {
        tareaSimple2.costoTotalTarea() shouldBe 20300
      }
      it("una tarea que lleva 30 horas finalizar, con 3 empleados con sueldos de 110, 120, 130 y un costo de infraestructura de 2000 debe tener un costo final de 31700") {
        tareaSimple3.costoTotalTarea() shouldBe 31700
      }
    }
  }
})
