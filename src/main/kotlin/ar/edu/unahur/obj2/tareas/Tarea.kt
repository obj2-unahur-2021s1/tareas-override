package ar.edu.unahur.obj2.tareas

/*interface tarea, class simple, clas integracion*/

open class Empleado(val sueldoPorHs: Int){}

class Responsable(sueldoPorHs: Int): Empleado(sueldoPorHs){}

abstract class Tarea(var responsable: Responsable) {
    var nominaEmpleados = mutableListOf<Empleado>()

    abstract fun costoTarea(): Int
    abstract fun horasNecesarias(): Int
}




class Simple(var horasEstimadas: Int, responsable: Responsable, var costoInfraestructura: Int): Tarea(responsable){

    fun agregarEmpleado(unEmpleado: Empleado){
        if(nominaEmpleados.isEmpty()){ nominaEmpleados.add(responsable) }
        nominaEmpleados.add(unEmpleado)
    }

    fun empleadosSinEncargado(): List<Empleado> {
       var soloEmpleados = nominaEmpleados

        soloEmpleados.remove(responsable)

        return soloEmpleados}


    override fun horasNecesarias() = horasEstimadas / empleadosSinEncargado().size

    fun costoSoloEmpleados(): Int { return empleadosSinEncargado().sumBy { e -> e.sueldoPorHs }}

    override fun costoTarea(): Int {
       return costoInfraestructura + horasEstimadas * responsable.sueldoPorHs + horasNecesarias() * costoSoloEmpleados()
    }

}

class Integracion(responsable: Responsable): Tarea(responsable){
    var listaTareas = mutableListOf<Tarea>()


    fun agregarTarea(unaTarea: Tarea){
        listaTareas.add(unaTarea)
    }

    override fun horasNecesarias(): Int {
        var totalHoras = listaTareas.sumBy { it.horasNecesarias() }

        return totalHoras + totalHoras/8
    }

    override fun costoTarea(): Int {
        var costoLista = listaTareas.sumBy { it.costoTarea() }

        var bonus = (costoLista*3)/100

        return  bonus + costoLista
    }

    fun nominaIntegracion(){

        nominaEmpleados.add(responsable)

        listaTareas.forEach { nominaEmpleados.addAll(it.nominaEmpleados) }

    }
}