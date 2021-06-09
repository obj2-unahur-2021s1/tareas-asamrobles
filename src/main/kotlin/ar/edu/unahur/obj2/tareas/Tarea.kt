package ar.edu.unahur.obj2.tareas

interface TareaComportamiento {
    fun horasNecesariasParaFinalizar(): Int
    fun costoTotalTarea(): Double
    fun nominaDeEmpleados(): List<Empleado>
}

class Tarea (val horasEstimadas : Int,val costoDeInfraestructura : Double, val responsable: Responsable): TareaComportamiento {

    override fun horasNecesariasParaFinalizar() = this.horasEstimadas / responsable.cantDeEmpleados()

    fun costoResponsable () = responsable.cuantoCobraPorHora * horasEstimadas
    fun costoPorEmpleados() = responsable.empleados.map { it.cuantoCobraPorHora * this.horasNecesariasParaFinalizar() }
    fun costoTotalDeEmpleados () = this.costoPorEmpleados().sum()

    override fun costoTotalTarea () = costoResponsable() + costoDeInfraestructura + this.costoTotalDeEmpleados()

    override fun nominaDeEmpleados(): MutableList<Empleado> {
        val nomina = mutableListOf<Empleado>(responsable)
        nomina.addAll(responsable.empleados)
        return nomina
    }
}

class TareaIntegracion(val responsable: Responsable): TareaComportamiento {
    val subtareas = mutableListOf<TareaComportamiento>()

    fun agregarSubtarea(subtarea: TareaComportamiento) { subtareas.add(subtarea)}

    fun horasMasPlanificacion(horasTrabajo: Int) = (horasTrabajo * 1.125).toInt()

    override fun horasNecesariasParaFinalizar() = subtareas.sumBy { horasMasPlanificacion(it.horasNecesariasParaFinalizar()) }

    override fun costoTotalTarea() = subtareas.sumByDouble { it.costoTotalTarea() } * 1.03

    fun listaNominas() = subtareas.map { it.nominaDeEmpleados() }.flatten()

    override fun nominaDeEmpleados(): MutableList<Empleado> {
        val nominaTotal = mutableListOf<Empleado>(responsable)
        nominaTotal.addAll(this.listaNominas())
        return nominaTotal
    }
}