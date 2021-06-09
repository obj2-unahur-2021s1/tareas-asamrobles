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
    val responsable300 = Responsable(300.0, mutableListOf())
    val responsable350 = Responsable(350.0, mutableListOf())

    //tareas comunes
    val tareaSimple1 = Tarea(50, 1000.0, responsable100)
    val tareaSimple2 = Tarea(80, 1500.0, responsable150)
    val tareaSimple3 = Tarea(90, 2000.0, responsable200)

    //tareas de integracion
    val tareaIntegracion2 = TareaIntegracion(responsable300)
    val tareaIntegracion1 = TareaIntegracion(responsable350)

    //Acciones
    tareaIntegracion2.agregarSubtarea(tareaSimple2)
    tareaIntegracion2.agregarSubtarea(tareaSimple3)

    tareaIntegracion1.agregarSubtarea(tareaSimple1)
    tareaIntegracion1.agregarSubtarea(tareaIntegracion2)

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
    describe("Req 4: tereas de integracion") {
      describe("horas necesarias para finalizar una tarea de integracion") {

        it("horas necesarias para finalizar tareaIntegracion2 que son la suma de las horas que tardan tareaSimple1 y tareaSimple2 mas las horas por planificacion deben ser 55") {
          tareaIntegracion2.horasNecesariasParaFinalizar() shouldBe 55
        }
        it("horas necesarias para finalizar tareaIntegracion1 que son la suma de las horas que tardan tareaSimple1 mas la suma de las subtareas de tareaIntegracion 2 mas las horas por planificacion deben ser 72") {
          tareaIntegracion1.horasNecesariasParaFinalizar() shouldBe 72
        }
      }

      describe("costo total de una tarea de integracion") {

        it("costo de tareaIntegracion2 que es la suma de los costos de sus subtareas mas un bonus para el responsable del 3% por cada tarea de integracion debe dar 61800") {
          tareaIntegracion2.costoTotalTarea() shouldBe 53560.0
        }
        it("costo de tareaIntegracion1 que es la suma de los costos de sus subtareas mas un bonus para el responsable del 3% por cada tarea de integracion debe dar 63406.8") {
          tareaIntegracion1.costoTotalTarea() shouldBe 63406.8
        }
      }

      describe("nomina de una tarea de integracion") {

        val nominaTareaIntegracion2 = tareaSimple2.nominaDeEmpleados()
        nominaTareaIntegracion2.addAll(tareaSimple3.nominaDeEmpleados())
        nominaTareaIntegracion2.add(responsable300)

        val nominaTareaIntegracion1 = tareaSimple1.nominaDeEmpleados()
        nominaTareaIntegracion1.addAll(nominaTareaIntegracion2)
        nominaTareaIntegracion1.add(responsable350)

        it("la nomina de tareaIntegracion2 debe estar fomada por su responsable y por las nominas de tareaSimple2 y tareaSimple3 y sus respectivos responsables") {
          tareaIntegracion2.nominaDeEmpleados().shouldContainExactlyInAnyOrder(nominaTareaIntegracion2)
        }
        it("la nomina de tareaIntegracion1 debe estar fomada por su responsable y por las nominas de tareaSimple1 y tareaIntegracion2 que a su vez se conforma de la nomina de dos subtareas simples") {
          tareaIntegracion1.nominaDeEmpleados().shouldContainExactlyInAnyOrder(nominaTareaIntegracion1)
        }
      }
    }
  }
})
