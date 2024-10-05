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

            File archivo = new File("C:\\Users\\GRIMM\\IdeaProjects\\XMLconSAX\\src\\sales.xml");


            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(archivo);
            doc.getDocumentElement().normalize();


            Map<String, Double> ventasPorDepartamento = new HashMap<>();


            NodeList listaVentas = doc.getElementsByTagName("sale_record");


            for (int i = 0; i < listaVentas.getLength(); i++) {
                Element venta = (Element) listaVentas.item(i);


                String departamento = venta.getElementsByTagName("department").item(0).getTextContent();
                String ventasTexto = venta.getElementsByTagName("sales").item(0).getTextContent();
                double ventas = Double.parseDouble(ventasTexto);


                ventasPorDepartamento.put(departamento, ventasPorDepartamento.getOrDefault(departamento, 0.0) + ventas);
            }


            System.out.println("Ventas por departamento:");
            double ventasTotales = 0.0;
            for (Map.Entry<String, Double> entry : ventasPorDepartamento.entrySet()) {
                System.out.println(entry.getKey() + ": $" + entry.getValue());
                ventasTotales += entry.getValue();  // Acumular ventas totales
            }


            System.out.println("Ventas Totales: $" + ventasTotales);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
