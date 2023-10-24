import java.util.List;
import java.util.function.UnaryOperator;

public class Ejercicio09 {
	public static List<String> convertirMayusculas(List<String> palabras) {

//		palabras.replaceAll(new UnaryOperator<String>() { // Con el unaryOperator recorro toda la lista de string sin usar un for y aplica en todas los cambios con el apply de abajo del override.
//			
//			@Override
//			public String apply(String t) {
//				return t.toUpperCase();
//			}
//		});
		
		palabras.replaceAll(s -> s.toUpperCase()); // Funciones landa, mejor manera.
		return palabras;
	}

}
