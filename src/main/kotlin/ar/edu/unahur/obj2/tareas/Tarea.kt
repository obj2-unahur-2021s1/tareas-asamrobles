package ar.edu.unahur.obj2.tareas

class Tarea (val horasEstimadas : Int,val costoDeInfraestructura : Double, val responsable: Responsable) {

    fun horasNecesariasParaFinalizar() = this.horasEstimadas / responsable.cantDeEmpleados()

    fun costoResponsable () = responsable.cuantoCobraPorHora * horasEstimadas
    fun costoTotalDeEmpleados () = this.costoPorEmpleados().sum()
    fun costoPorEmpleados() = responsable.empleados.map { it.cuantoCobraPorHora *  this.horasNecesariasParaFinalizar() }
    fun costoTotalInfraestructura () = costoResponsable() + costoDeInfraestructura + this.costoTotalDeEmpleados()


}



class Responsable (val empleados: MutableList<Empleados>,val cuantoCobraPorHora :Int){

    fun cantDeEmpleados() = empleados.size


}

