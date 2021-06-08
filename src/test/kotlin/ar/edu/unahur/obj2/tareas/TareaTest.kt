package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  describe("Una tarea") {
    var unResponsable = Responsable(10)

    var empleadoUno = Empleado(5)
    var empleadoDos = Empleado(5)
    var empleadoTres = Empleado(5)

    var unaTarea = Simple(50,unResponsable, 150)
    var tareaDos = Simple(50,unResponsable, 150)
    var tareaTres = Simple(50,unResponsable, 150)

    unaTarea.agregarEmpleado(empleadoUno)
    unaTarea.agregarEmpleado(empleadoDos)
    unaTarea.agregarEmpleado(empleadoTres)
    unaTarea.nominaEmpleados.size.shouldBe(4)

    tareaDos.agregarEmpleado(empleadoUno)
    tareaDos.agregarEmpleado(empleadoDos)
    tareaDos.agregarEmpleado(empleadoTres)

    tareaTres.agregarEmpleado(empleadoUno)
    tareaTres.agregarEmpleado(empleadoDos)
    tareaTres.agregarEmpleado(empleadoTres)

    describe("lista empleados sin encargados"){
      unaTarea.empleadosSinEncargado().size.shouldBe(3)
    }

    describe("horas necesarias para finalizar tarea"){
      unaTarea.horasNecesarias().shouldBe(16)
    }

    describe("costo Solo empleados"){
      unaTarea.costoSoloEmpleados().shouldBe(15)
    }

    describe("costo tarea"){
      unaTarea.costoTarea().shouldBe(890)
    }

    describe("Tarea integradora"){
      var tareaIntegradora = Integracion(unResponsable)

      tareaIntegradora.agregarTarea(unaTarea)
      tareaIntegradora.agregarTarea(tareaDos)
      tareaIntegradora.agregarTarea(tareaTres)

      it("horas necesarias"){
        tareaIntegradora.horasNecesarias().shouldBe(54)
      }
      it("costo Tarea"){
       tareaIntegradora.costoTarea().shouldBe(2750)
      }
      it("nominaIntegracion"){
        tareaIntegradora.nominaIntegracion()
        tareaIntegradora.nominaEmpleados.size.shouldBe(13)
      }

    }

  }
})
