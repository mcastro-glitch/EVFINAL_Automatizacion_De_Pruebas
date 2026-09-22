Proyecto de Automatización de Pruebas - Examen Final
Descripción del Proyecto
Este repositorio contiene la implementación de una estrategia de pruebas automatizadas y un pipeline CI/CD completo, estructurado bajo el paquete evfinal. El desarrollo utiliza un flujo Trunk-Based, gestionando de forma automatizada el ciclo de vida del software: desde la obtención del código fuente y su compilación, hasta la validación multicapa y su despliegue seguro en un entorno de pruebas Blue-Green.

Estrategia de Pruebas Implementada
La estrategia de aseguramiento de calidad se divide en tres niveles integrados en el pipeline:

Pruebas Unitarias (CalculadoraTest.java): Implementadas con JUnit 5, se ejecutan en las etapas tempranas para validar la lógica de negocio de forma aislada (Fail Fast).

Pruebas de Integración (CalculadoraIT.java): Gestionadas por el plugin Failsafe de Maven, verifican la correcta interacción de los componentes y servicios simulados.

Pruebas de Aceptación / Acceptance Gate (LoginAcceptanceTest.java): Diseñadas para validar flujos críticos (ej. Interfaz de usuario con Selenium) directamente contra el entorno de Staging (Green).

Mecanismo de Rollback: Si el Acceptance Gate o cualquier prueba previa falla, el bloque post del Jenkinsfile intercepta el error y detona un script de recuperación para restaurar el tráfico hacia la versión estable (Blue), previniendo incidentes en producción.

Instrucciones de Ejecución
Para replicar y validar la ejecución del pipeline localmente sin depender de un servidor Jenkins, siga estos pasos:

Clonar el repositorio:
git clone [https://github.com/mcastro-glitch/EVFINAL_Automatizacion_De_Pruebas.git](https://github.com/mcastro-glitch/EVFINAL_Automatizacion_De_Pruebas.git)

Navegar al directorio del proyecto:
cd EVFINAL_Automatizacion_De_Pruebas

Compilar el proyecto (sin pruebas):
mvn clean package -DskipTests

Ejecutar flujo completo (Pipeline Local):
Ejecute mvn clean verify. Este comando desencadenará la compilación, las pruebas unitarias (Surefire), las pruebas de integración (Failsafe) y las pruebas de aceptación.

Nota sobre la evidencia de Rollback: Para visualizar el disparador del mecanismo de rollback, altere deliberadamente un valor esperado en CalculadoraTest.java y vuelva a ejecutar mvn clean verify. El fallo resultante es el evento exacto que el Jenkinsfile utiliza para abortar el despliegue e iniciar la recuperación.