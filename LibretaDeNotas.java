package clase270824;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

/**Modificar la Clase Evaluador:
  para que pueda evaluar y almacenar las calificaciones de una lista de estudiantes en lugar de una única calificación.

 Menú de Opciones:
 Muestra un menú con las siguientes opciones:
 1. Mostrar el Promedio de Notas por Estudiante.
 2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.
 3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.
 0. Salir del Menú.
 Utiliza un bucle para permitir al usuario seleccionar opciones hasta que ingrese 0 para salir.
 Operaciones del Menú:
 Opción 1: Mostrar el Promedio de Notas por Estudiante.
 Muestra el promedio de notas por cada estudiante calculado previamente.
 Opción 2: Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.
 Solicita al usuario ingresar el nombre de un estudiante y una nota, luego verifica si es aprobatoria o reprobatoria según una nota de aprobación definida.
 Opción 3: Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.
 Solicita al usuario ingresar el nombre de un estudiante y una nota, luego verifica si está por sobre o por debajo del promedio general.
 Validaciones:
 Implementa validaciones para asegurar que las notas ingresadas estén en un rango válido y que la cantidad de alumnos sea un número positivo.***/
public class LibretaDeNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, ArrayList<Integer>> notasPorEstudiante = new HashMap<>();

        System.out.println("Ingrese la cantidad de alumnos");
        int cantidadAlumnos = sc.nextInt();
        sc.nextLine(); // quiero ver si esto no me da el salto de línea
        //acá le digo al for que pare cuando llegue a la cantidad de alumnos ingresada
        for (int i = 0; i < cantidadAlumnos; i++) {

            System.out.println("Ingrese el nombre del alumno (no se puede repetir)");
            String nombreAlumno = sc.next();

            //acá le digo al for que vaya poniendo los nombres ingresados como key del hashmap
            notasPorEstudiante.put(nombreAlumno, new ArrayList<>());
            System.out.println("Ingrese la cantidad total de notas de " + nombreAlumno);
            int notasEnTotal = sc.nextInt();
            sc.nextLine();
            //for anidado para que este pare cuando llegue a la cantidad de notas que ingresen
            for (int x = 0; x < notasEnTotal; x++) {
                System.out.println("Ingrese cada nota de " + nombreAlumno);
                int notas = sc.nextInt();
                if (notas < 0 || notas > 7) {
                    System.out.println("Valor inválido, ejecuta el programa de nuevo.");
                }else {
                    notasPorEstudiante.get(nombreAlumno).add(notas);
                }

            }
        }


        //verificar si se guardaron bien los datos
       /*for (String nombreAlumno : notasPorEstudiante.keySet()) {
            System.out.println("Nombre: " + nombreAlumno + '\n' + "Notas: " + notasPorEstudiante.get(nombreAlumno));
        }*/

       //MENU DE OPCIONES
        int Menu;
        while (true) {
            System.out.println("Ingrese una opción \n 1) Mostrar el Promedio de Notas por Estudiante.\n 2) Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.\n 3) Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.\n 0) Salir del Menú.");
            Integer opcion = sc.nextInt();
            if (opcion == 0) {
                System.out.println("Chaito");
                break;
            } else {
                switch (opcion) {
                    case 1:
                        for (String nombreAlumno : notasPorEstudiante.keySet()) {
                            ArrayList<Integer> notas = notasPorEstudiante.get(nombreAlumno);
                            float suma = 0;
                            for (int y = 0; y < notas.size(); y++) {
                                suma += notas.get(y);
                            }
                            float promedio = suma / notas.size();
                            System.out.println("El promedio de " + nombreAlumno + " es: " + promedio + "\n" + "Nota Máxima = " + Collections.max(notas) + '\n' + "Nota Mínima = " + Collections.min(notas));
                        }
                        break;
                        case 2:
                            //promedio sobre 4 o bajo 4
                            for (String nombreAlumno : notasPorEstudiante.keySet()) {
                                ArrayList<Integer> notas = notasPorEstudiante.get(nombreAlumno);
                                float suma = 0;
                                for (int y = 0; y < notas.size(); y++) {
                                    suma += notas.get(y);
                                }
                                float promedio = suma / notas.size();
                                if (promedio > 4.0f) {
                                    System.out.println(nombreAlumno + " aprobó con nota " + promedio);
                                }else {
                                    System.out.println(nombreAlumno + " reprobó con nota " + promedio);
                                }
                            }
                            break;
                            case 3:
                                float sumaCurso = 0;
                                int cantidadNotas = 0;
                                for (String nombreAlumno : notasPorEstudiante.keySet()) {
                                    ArrayList<Integer> notas = notasPorEstudiante.get(nombreAlumno);
                                    for (float nota : notas) {
                                        sumaCurso += nota;
                                        cantidadNotas++;
                                    }
                                }
                                float promedioCurso = sumaCurso / cantidadNotas;
                                for (String nombreAlumno : notasPorEstudiante.keySet()) {
                                    ArrayList<Integer> notas = notasPorEstudiante.get(nombreAlumno);
                                    float suma = 0;
                                    for (float nota : notas) {
                                        suma += nota;
                                    }
                                    float promedioAlumno = suma / notas.size();
                                    if (promedioAlumno > promedioCurso) {
                                        System.out.println(nombreAlumno + " tiene un promedio mayor que el promedio del curso (" + promedioAlumno + " vs " + promedioCurso + ")");
                                    } else if (promedioAlumno < promedioCurso) {
                                        System.out.println(nombreAlumno + " tiene un promedio menor que el promedio del curso (" + promedioAlumno + " vs " + promedioCurso + ")");
                                    } else {
                                        System.out.println(nombreAlumno + " tiene un promedio igual al promedio del curso (" + promedioAlumno + " vs " + promedioCurso + ")");
                                    }
                                }
                                break;

                                /*INTENTO FALLIDO, ESTUVO CASI
                                float sumaCurso = 0;
                                int cantidadNotas = 0;
                                //promedio del curso, evaluar si el promedio del alumno es > o <
                                for (String nombreAlumno : notasPorEstudiante.keySet()) {
                                    ArrayList<Integer> notas = notasPorEstudiante.get(nombreAlumno);
                                    for (float nota : notas) {
                                        sumaCurso += nota;
                                        cantidadNotas++;
                                    }
                                }
                                float promedioCurso = sumaCurso / cantidadNotas;
                                for (String nombreAlumno : notasPorEstudiante.keySet()) {
                                    ArrayList<Integer> notas = notasPorEstudiante.get(nombreAlumno);
                                    float suma = 0;
                                    for (float nota : notas) {
                                        suma += nota;
                                        float promedioAlumno = suma / notas.size();
                                        if (promedioAlumno > promedioCurso) {
                                            System.out.println("El promedio de " + nombreAlumno + " es mayor que el promedio del curso que fue: " + promedioCurso);
                                        } else if (promedioAlumno < promedioCurso) {
                                            System.out.println("El promedio de " + nombreAlumno + " es menor que el promedio del curso que fue: " + promedioCurso);
                                        }else {
                                            System.out.println("El promedio de " + nombreAlumno + " es igual que el promedio del curso (" + promedioCurso + ")");
                                    }
                                }
                                    break;*/



                                    /*if(nota < promedioCurso) {
                                        System.out.println("El promedio de " + nombreAlumno + " es inferior al promedio del curso que es " + promedioCurso);
                                    } else if (promedio > promedioCurso) {
                                        System.out.println("El promedio de " + nombreAlumno + " es superior al promedio del curso que es " + promedioCurso);*/

                                    }
                                }
                }
            }

        }



