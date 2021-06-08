package ar.edu.unahur.obj2.tareas

/*interface tarea, class simple, clas integracion*/

open class Empleado(val sueldoPorHs: Int){}

class Responsable(sueldoPorHs: Int): Empleado(sueldoPorHs){}

interface Tarea {

    fun costoTarea(): Int
    fun horasNecesarias(): Int
}




class Simple(var horasEstimadas: Int, var responsable: Responsable, var costoInfraestructura: Int): Tarea{
    var nominaEmpleados = mutableListOf<Empleado>()


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

class Integracion(var responsable: Responsable): Tarea{
    var listaTareas = mutableListOf<Tarea>()
    var nominaEmpleados = nominaIntegracion()

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

    fun nominaIntegracion(): List<Empleado>{
        var nomina = mutableListOf<Empleado>()
        nomina.add(responsable)

        listaTareas.forEach { nomina.addAll(it.nominaEmpleados) }

        return nomina
    }
}