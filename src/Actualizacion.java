public class Actualizacion {

    public static void ejecutarScript(){
        byte[] contenido = leerContenido();
        int id = encontrarId();
        actualizarFormula(contenido, id);
    }



    public static byte[] leerContenido(){

    }

    public static int encontrarId(){

    }

    public static void actualizarFormula(byte[] contenido, int id){
        try {
            String query = "UPDATE FO_FORMULAFLUJO SET FLUJOEJECUCION = ?  WHERE IDFORMULA = ?";//Aquí se tiene una cadena que encapsula una consulta preparada para evitar las inyecciones sql
            Conexion con = new Conexion();//Se crea un objeto de tipo Conexion, clase en donde se aloja la conexión con la base de datos
            con.Conexion();//Se ejecuta el método constructor
            con.sentencia = con.connection.prepareStatement(query);//Uno de los atributos del objeto "con" es otro objeto de tipo PreparedStatement, su argumento es la consulta "query"

            con.sentencia.setBytes(1,contenido);//El primer parametro quiere decir el orden de aparición de "?", y el segundo parametro lo que reemplaza a "?" en la consulta
            con.sentencia.setInt(2,id);

            int filasActualizadas = con.sentencia.executeUpdate();//Este método ejecuta la consulta y devuelve un Int con la cantidad de filas afectadas
            System.out.println(filasActualizadas+" fila(s) actualizada(s)");

            con.sentencia.close();
            con.connection.close();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //Tipos de datos de la tabla FO_FORMULA
    /*DATA_TYPE
        bigint
        nvarchar
        nvarchar
        bigint
        bigint
        char
        datetime ('AAAA-MM-DD HH:MI:SS')
        nvarchar
        bigint
        bigint
        bigint
        nvarchar
        nvarchar*/
//ATRIBUTOS DE LA TABLA FO_FORMULA
    /*[NOMBRE]
      ,[DESCRIPCION]
      ,[IDTIPOFORMULA]
      ,[VLOCALVERSION]
      ,[VPRIVATEELEMENT]
      ,[VSYNCTIMESTAMP]
      ,[VPATH]
      ,[VSYNCVERSION]
      ,[IDCOMPANIA]
      ,[IDCLASEFORMULA]
      ,[FORMULABASE]
      ,[IDUNICO]*/
// INSERT INTO FO_FORMULA (NOMBRE, DESCRIPCION, IDTIPOFORMULA, VPRIVATEELEMENT, IDCOMPANIA, IDCLASEFORMULA, FORMULABASE) VALUES ('pago nuevo', 'Se crea un nuevo pago', 2, 'N', 935, 2, 'N' );

}
