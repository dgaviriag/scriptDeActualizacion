import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Actualizacion {

    //Aqui se llaman los métodos para realizar la actualización de la fórmula
    public static void ejecutarScript(){
        File [] files = Archivo.obtenerArchivos();
        try {
            //Realiza la actualización por cada archivo:
            for (File file : files) {
                byte[] contenido = Archivo.leerContenido(file);
                int id = encontrarId(Archivo.obtenerNombreSinFormato(file));//no devolver, si es -1 debe tirar una expeción
                actualizarFormula(contenido, id);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }



    //En este método se hace un select en la base de datos para ver si el nombre de alguna formula coincide con el del archivo y se devuelve el id
    public static int encontrarId(String nombreArchivo){
        Conexion con = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try{
            String query = "SELECT ID FROM FO_FORMULA WHERE FO_FORMULA.NOMBRE = ?";
            con = new Conexion();
            sentencia = con.connection.prepareStatement(query);
            sentencia.setString(1, nombreArchivo);
            resultado= sentencia.executeQuery();

            int id = -1;
            while(resultado.next()){
                id= Integer.parseInt(resultado.getString("ID"));
                System.out.println(resultado.getString("ID"));
            }

            resultado.close();
            sentencia.close();
            con.connection.close();

            return id;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return -1;
        }finally {
            try{
                if (resultado!=null){
                    resultado.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            try{
                if (sentencia!=null){
                    sentencia.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            try{
                if (con!=null){
                    con.connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public static void actualizarFormula(byte[] contenido, int id){
        PreparedStatement sentencia = null;
        Conexion con = null;

        try {
            String query = "UPDATE FO_FORMULAFLUJO SET FLUJOEJECUCION = ?  WHERE IDFORMULA = ?";//Aquí se tiene una cadena que encapsula una consulta preparada para evitar las inyecciones sql
            con = new Conexion();//Se crea un objeto de tipo Conexion, clase en donde se aloja la conexión con la base de datos
            //Se ejecuta el método constructor
            sentencia = con.connection.prepareStatement(query);//Uno de los atributos del objeto "con" es otro objeto de tipo PreparedStatement, su argumento es la consulta "query"

            sentencia.setBytes(1,contenido);//El primer parametro quiere decir el orden de aparición de "?", y el segundo parametro lo que reemplaza a "?" en la consulta
            sentencia.setInt(2,id);

            int filasActualizadas = sentencia.executeUpdate();//Este método ejecuta la consulta y devuelve un Int con la cantidad de filas afectadas
            System.out.println(filasActualizadas+" fila(s) actualizada(s)");

        //A continuación se manejan las excepciones y se cierran las conexiones
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            try{
                if (sentencia!=null){
                    sentencia.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            try {
                if (con!=null){
                    con.connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
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
