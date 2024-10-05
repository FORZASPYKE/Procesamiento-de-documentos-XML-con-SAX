import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Cargar el archivo XML
            File archivo = new File("C:\\Users\\GRIMM\\IdeaProjects\\XMLconSAX\\src\\sales.xml");

            // 2. Preparar el lector de XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(archivo);
            doc.getDocumentElement().normalize();

            // 3. Mapa para almacenar las ventas por departamento
            Map<String, Double> ventasPorDepartamento = new HashMap<>();

            // 4. Obtener todos los registros de ventas
            NodeList listaVentas = doc.getElementsByTagName("sale_record");

            // 5. Recorrer cada venta y sumarla al departamento correspondiente
            for (int i = 0; i < listaVentas.getLength(); i++) {
                Element venta = (Element) listaVentas.item(i);

                // Obtener el departamento y las ventas
                String departamento = venta.getElementsByTagName("department").item(0).getTextContent();
                String ventasTexto = venta.getElementsByTagName("sales").item(0).getTextContent();
                double ventas = Double.parseDouble(ventasTexto);

                // Sumar las ventas al departamento en el mapa
                ventasPorDepartamento.put(departamento, ventasPorDepartamento.getOrDefault(departamento, 0.0) + ventas);
            }

            // 6. Mostrar las ventas por departamento
            System.out.println("Ventas por departamento:");
            double ventasTotales = 0.0;
            for (Map.Entry<String, Double> entry : ventasPorDepartamento.entrySet()) {
                System.out.println(entry.getKey() + ": $" + entry.getValue());
                ventasTotales += entry.getValue();  // Acumular ventas totales
            }

            // 7. Mostrar las ventas totales
            System.out.println("Ventas Totales: $" + ventasTotales);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
