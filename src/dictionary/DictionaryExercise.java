package dictionary;

import java.util.Scanner;

import application.Exercise;

public class DictionaryExercise extends Exercise {
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleArrayDictionary<String,Product> items;
	
	public DictionaryExercise(Scanner scanner) {
		super(scanner);
		this.items = new SimpleArrayDictionary<String,Product>();
	}
	
	@Override
	protected void exerciseLogic() {
		if (firstTime) System.out.println("Bienvenido a Inventory Management!\n");
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			addItem();
			break;
		case 2:
			editItem();
			break;
		case 3:
			removeItem();
			break;
		case 4:
			getItemMenu();
			break;
		case 5:
			getReport();
			break;
		}
	}
	
	private void menuLogic() {
		firstTime = false;
		
		if (items.isEmpty()) {
			System.out.println("No hay productos en el sistema.");
		}
		
		System.out.println("Ingrese la opción a ejecutar:");
		System.out.println("1 - Ingresar Productos");
		System.out.println("2 - Editar producto"); 
		System.out.println("3 - Eliminar productos");
		System.out.println("4 - Buscador de Productos");
		System.out.println("5 - Exportar Reporte");
		System.out.println("6 - Volver al menú principal");
		
		String userInput = scanner.nextLine().toLowerCase();
		
		switch(userInput) {
		case "1":
			currentPhase = 1;
			break;
		case "2":
			currentPhase = 2;
			break;
		case "3":
			currentPhase = 3;
			break;
		case "4":
			currentPhase = 4;
			break;
		case "5":
			currentPhase = 5;
			break;
		case "6":
			running = false;
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			currentPhase = 0;
			break;
		}	
	}

	private void addItem() {
		System.out.println("Agregando un nuevo producto.\n");
		
		System.out.println("Ingrese el código único para el producto (SKU)\n");
		String productID = scanner.nextLine();
		
		if (items.containsKey(productID)) {
			System.out.println("Producto ya existe en el sistema.");
			currentPhase = 0;
		} else {
			System.out.println("Ingrese el nombre del producto:\n");
			String productName = scanner.nextLine();
			
			System.out.println("Ingrese el precio del producto:\n");
			double productPrice = readDouble();
			
			System.out.println("Ingrese la cantidad de stock del producto:\n");
			int productStock = readInt();
			
			Product newProduct = new Product(productID,productName,productPrice,productStock);
			
			try{
				items.put(newProduct.productID, newProduct);
				System.out.println("Producto Agregado con éxito");
			} catch (Exception e) {
				System.out.println("Error insertando producto: " + e);
			}	
			
			askInput("add");
		}		
	}
	
	private void getItemMenu() {
		System.out.println("Metodo de Búsqueda:\n");
		System.out.println("1 - Nombre:\n");
		System.out.println("2 - Codigo Unico (SKU):\n");
		String productSearch = scanner.nextLine();
		
		switch (productSearch) {
		case "1":
			getItemByName();
			break;
		case "2":
			getItemById();
			break;
		default:
			System.out.println("Método de búsqueda no encontrado.");
			break;
		}
	}
	
	private void getItemByName() {
		//String userSearch = scanner.nextLine();
		System.out.println("Not implemented");
	}
	
	private void removeItem() {
		System.out.println("Ingrese el ID del producto a eliminar:\n");
		String productID = scanner.nextLine();
		
		boolean removedFlag = items.remove(productID);
		
		if (removedFlag) {
			System.out.println("Elemento: " + productID + " eliminado con éxito.");
		} else {
			System.out.println("Producto no encontrado, intente nuevamente.");
		}
		
		askInput("delete");
		
	}
	
	private void editItem() {
		System.out.println("Ingrese el ID del producto a editar:\n");
		String productID = scanner.nextLine();
		
		if (!items.containsKey(productID)) {
			System.out.println("Producto no encontrado en el sistema.");
			currentPhase = 0;
		} else {
			Product product = items.get(productID);
			System.out.println("Actualmente editando: " + getProductDetails(product) + "\n");
			
			boolean isChangingAtttributes = true;
			
			while (isChangingAtttributes) {
				System.out.println("Seleccione el atributo a eliminar: \n");
				System.out.println("1 - Editar Precio\n");
				System.out.println("2 - Editar Cantidad en Stock\n");
				System.out.println("3 - Confirmar\n");
				System.out.println("4 - Salir\n");
				String userOption = scanner.nextLine();
				switch (userOption) {
				case "1":
					System.out.println("Ingrese nuevo precio: ");
					double newPrice = readDouble();
					product.productPrice = newPrice;
					break;
				case "2":
					System.out.println("Ingrese nueva cantidad de stock: ");
					int newQty = readInt();
					product.productQuantity = newQty;
					break;
				case "3":
					isChangingAtttributes = false;
					break;
				case "4":
					currentPhase = 0;
					break;
				default:
					System.out.println("Entrada invalida.");
				}
			}
			
			items.put(productID, product);
			askInput("edit");
		}
		
	}
	
	private void getItemById() {
		System.out.println("Ingrese el ID del producto a buscar:\n");
		String userSearch = scanner.nextLine();
		
		Product foundProduct = items.get(userSearch);
		
		if (foundProduct != null) {
			System.out.println("Producto encontrado:");
			System.out.println(getProductDetails(foundProduct));
			
		} else {
			System.out.println("Producto no encontrado, intente nuevamente.");
			getItemById();
		}
		
		askInput("search");
		
	}
	

	private void getReport() {
		
		if (items.isEmpty()) {
			System.out.println("No hay productos en el sistema. Volviendo al menú principal.\n");
			currentPhase = 0;
		} else { 
			
			double totalProductPrice = 0;
			
			Object[] keys = items.keys(); // Parseo a Object, para convertir luego las Keys a String.
			
			for (int i = 0; i < keys.length; i++) {
				String key = (String) keys[i];
				Product product = items.get(key);
				System.out.println(getProductDetails(product));
				totalProductPrice += (product.productPrice * product.productQuantity);
			}
			System.out.println("Precio total de mercadería: " + totalProductPrice);
			currentPhase = 0;
		}
	}
	
	private int readInt() {
	    while (true) {
	        String input = scanner.nextLine();
	        try {
	            return Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Intentá nuevamente.");
	        }
	    }
	}
	
	private double readDouble() {
	    while (true) {
	        String input = scanner.nextLine();

	        try {
	            return Double.parseDouble(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Intentá nuevamente.");
	        }
	    }
	}
	
	private void askInput(String method) {
		
		if (method == "delete") System.out.println("Desea remover otro producto? (s/n)\n");
		else if (method == "add") System.out.println("Desea agregar otro producto? (s/n)\n");
		else if (method == "search") System.out.println("Desea buscar otro producto? (s/n)\n");
		else if (method == "edit") System.out.println("Desea editar otro producto? (s/n)\n");
		
		String input = scanner.nextLine();
		
		switch(input) {
		case "s":
			switch (method) {
			case "delete":
				currentPhase = 3;
				break;
			case "search":
				currentPhase = 4;
				break;
			case "edit":
				currentPhase = 2;
				break;
			case "add":
				currentPhase = 1;
				break;
			}
		case "n":
			currentPhase = 0;
			break;
		default:
			System.out.println("Entrada Inválida, Intente Nuevamente");
			askInput(method);
			break;
		}
	}
	
	private String getProductDetails(Product product) {
		return "Nombre: " + product.productName + " Precio: " + product.productPrice + " Unidades en Stock: " + product.productQuantity + "\n";
	}
	
}
