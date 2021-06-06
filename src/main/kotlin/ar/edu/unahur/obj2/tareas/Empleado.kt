package ar.edu.unahur.obj2.tareas

open class Empleado(val cuantoCobraPorHora :Double) {
}

class Responsable(cuantoCobraPorHora :Double, val empleados: MutableList<Empleado>): Empleado(cuantoCobraPorHora) {
    fun cantDeEmpleados() = empleados.size
}


