package ar.edu.unahur.obj2.tareas

abstract class Empleado(val cuantoCobraPorHora: Double) {
}

class EmpleadoComun(cuantoCobraPorHora: Double): Empleado(cuantoCobraPorHora) {
}

class Responsable(cuantoCobraPorHora: Double, val empleados: MutableList<Empleado>): Empleado(cuantoCobraPorHora) {
    fun cantDeEmpleados() = empleados.size
}




