package ar.edu.unahur.obj2.tareas

class Tarea (val horasEstimadas : Int,val costoDeInfraestructura : Double, val responsable: Responsable) {

    fun horasNecesariasParaFinalizar() = this.horasEstimadas / responsable.cantDeEmpleados()

    fun costoResponsable () = responsable.cuantoCobraPorHora * horasEstimadas
    fun costoPorEmpleados() = responsable.empleados.map { it.cuantoCobraPorHora * this.horasNecesariasParaFinalizar() }
    fun costoTotalDeEmpleados () = this.costoPorEmpleados().sum()
    fun costoTotalTarea () = costoResponsable() + costoDeInfraestructura + this.costoTotalDeEmpleados()

    fun nominaDeEmpleados(): MutableList<Empleado> {
        var nomina = mutableListOf<Empleado>(responsable)
        nomina.addAll(responsable.empleados)
        return nomina
    }
}

