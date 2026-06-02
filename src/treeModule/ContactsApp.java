package treeModule;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.Exercise;
import list.SimpleArrayList;

public class ContactsApp extends Exercise{
	private int currentPhase = 0;
	private boolean firstTime = true;
	BST<Contact> contactList;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public ContactsApp(Scanner scanner) {
		super(scanner);
		this.contactList = new BST<>();
	}

	@Override
	protected void exerciseLogic() {
		if(firstTime) {
			System.out.println("Bienvenido a Aplicacion Contactos.\n");
			firstTime = false;
		}

		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			createContact();
			break;
		case 2:
			searchContact();
			break;
		case 3:
			editContact();
			break;
		case 4:
			deleteContact();
			break;
		case 5:
			showContacts();
			break;
		case 6:
			loadDatabase();
			break;
		}
		
	}
	
	private void menuLogic() {
		
		if (contactList.isEmpty()) {
			System.out.println("No hay contactos - Utilice el menú 1 para crear un contacto");
		}
		else {
			System.out.println("Cantidad de Contactos: " + contactList.getSize());
		}

		System.out.println("\nIngrese la opción a ejecutar: ");
		System.out.println("1 - Nuevo Contacto");
		System.out.println("2 - Buscar Contacto");
		System.out.println("3 - Editar Contacto");
		System.out.println("4 - Eliminar Contacto");
		System.out.println("5 - Mostrar Agenda");
		System.out.println("6 - Cargar Base de Datos");
		System.out.println("7 - Volver al menú principal");
		
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
			currentPhase = 6;
			break;
		case "7":
			running = false;
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			currentPhase = 0;
			break;
		}
	}
	
	private void createContact() {

		System.out.println("Ingrese el Nombre: \n");
		String nombre = scanner.nextLine().toLowerCase();
		System.out.println("Ingrese el Apellido: \n");
		String apellido = scanner.nextLine().toLowerCase();
		System.out.println("Ingrese el Telefono: \n");
		int telefono = readInt(); 
		System.out.println("Ingrese el email: \n");
		String email = validateEmail();
		
		Contact newContact = new Contact(nombre, apellido, telefono, email);
		
		contactList.insert(newContact);
		
		System.out.println("Contacto creado exitosamente!\n");
		
		askInput("add");
	}
	
	private void editContact() {

	    SimpleArrayList<Contact> results = searchContact();

	    if(results == null || results.isEmpty()) {
	        currentPhase = 0;
	        return;
	    }

	    Contact selected = selectContact(results);

	    System.out.println("\nEditando:");
	    System.out.println(getContactDetails(selected));

	    System.out.println("\n1 - Nombre");
	    System.out.println("2 - Apellido");
	    System.out.println("3 - Telefono");
	    System.out.println("4 - Email");
	    System.out.println("5 - Cancelar");

	    String option = scanner.nextLine();

	    switch(option) {

	    case "1":

	        System.out.println("Nuevo nombre:");
	        String newName = scanner.nextLine();

	        contactList.remove(selected);

	        selected.setFirstName(newName);

	        contactList.insert(selected);

	        System.out.println("Nombre actualizado!");
	        break;

	    case "2":

	        System.out.println("Nuevo apellido:");
	        selected.setLastName(scanner.nextLine());

	        System.out.println("Apellido actualizado!");
	        break;

	    case "3":

	        System.out.println("Nuevo telefono:");
	        selected.setCellphone(readInt());

	        System.out.println("Telefono actualizado!");
	        break;
	        
	    case "4":
	        System.out.println("Nuevo Email:");
	        selected.setEmail(validateEmail());
	
	        System.out.println("Email actualizado!");
	        break;
	    }
	 
	    currentPhase = 0;
	}

	private void deleteContact() {
	    SimpleArrayList<Contact> results = searchContact();

	    if(results == null || results.isEmpty()) {
	        currentPhase = 0;
	        return;
	    }

	    Contact selected = selectContact(results);

	    System.out.println("\nEliminar:");
	    System.out.println(getContactDetails(selected));

	    System.out.println("Confirmar? (s/n)");

	    String confirm = scanner.nextLine();

	    if(confirm.equalsIgnoreCase("s")) {

	        contactList.remove(selected);

	        System.out.println("Contacto eliminado!");
	    }

	    currentPhase = 0;
	
	}
	
	private SimpleArrayList<Contact> searchContact() {
		SimpleArrayList<Contact> allContacts = contactList.inOrder();
		System.out.println("\n- Búsqueda de contactos - ");
		System.out.println("Seleccione el parámetro de búsqueda: \n");
		
		System.out.println("1 - Nombre");
		System.out.println("2 - Apellido");
		System.out.println("3 - Telefono");
		System.out.println("4 - Email");
		System.out.println("5 - Volver Atras");
		
		String userSelect = scanner.nextLine().toLowerCase();
		
		switch (userSelect) {
		case "1":
			SimpleArrayList<Contact> resultContactSearch = new SimpleArrayList<>();
			System.out.println("Ingrese el nombre a buscar: ");
			String firstNameSearch = scanner.nextLine().toLowerCase();			
			for (int i = 0; i < allContacts.size();i++) {
				Contact contact = allContacts.get(i);
				String contactName = contact.getFirstName().toLowerCase();
				if (contactName.startsWith(firstNameSearch)){
					resultContactSearch.add(contact);
				}
			}
			
			if (checkSearchResults(resultContactSearch)) {
				for (int j = 0; j < resultContactSearch.size(); j++) {
					System.out.println(j +" - " + getContactDetails(resultContactSearch.get(j)));
				}
				return resultContactSearch;
			} else {
				System.out.println("No hay elementos para la búsqueda realizada. \n");
			}
			break;
		case "2":
			System.out.println("Ingrese el apellido a buscar: ");
			
			String lastNameSearch = scanner.nextLine().toLowerCase();
			
			SimpleArrayList<Contact> resultContactSearchLN = new SimpleArrayList<>();
			for (int i = 0; i < allContacts.size();i++) {
				Contact contact = allContacts.get(i);
				String contactLName = contact.getLastName().toLowerCase();
				if (contactLName.startsWith(lastNameSearch)){
					resultContactSearchLN.add(contact);
				}
			}
			
			if (checkSearchResults(resultContactSearchLN)) {
				for (int j = 0; j < resultContactSearchLN.size(); j++) {
					System.out.println(j +" - " + getContactDetails(resultContactSearchLN.get(j)));
				}
				return resultContactSearchLN;
			} else {
				System.out.println("No hay elementos para la búsqueda realizada. \n");
			}
			break;
		case "3":
			System.out.println("Ingrese el telefono a buscar: ");
			String cellphoneSearch = scanner.nextLine().toLowerCase();
			
			SimpleArrayList<Contact> resultContactSearchT = new SimpleArrayList<>();
			for (int i = 0; i < allContacts.size();i++) {
				Contact contact = allContacts.get(i);
				int contactCellphone = contact.getCellphone();
				if (Integer.toString(contactCellphone).startsWith(cellphoneSearch)){
					resultContactSearchT.add(contact);
				}
			}
			if (checkSearchResults(resultContactSearchT)) {
				for (int j = 0; j < resultContactSearchT.size(); j++) {
					System.out.println(j +" - " + getContactDetails(resultContactSearchT.get(j)));
				}
				return resultContactSearchT;
			} else {
				System.out.println("No hay elementos para la búsqueda realizada. \n");
			}
			break;
		case "4":
			System.out.println("Ingrese el Email a buscar: ");
			String emailSearch = scanner.nextLine().toLowerCase();
			
			SimpleArrayList<Contact> resultContactSearchEmail = new SimpleArrayList<>();
			for (int i = 0; i < allContacts.size();i++) {
				Contact contact = allContacts.get(i);
				String contactEmail = contact.getEmail();
				if (contactEmail.startsWith(emailSearch)){
					resultContactSearchEmail.add(contact);
				}
			}
			if (checkSearchResults(resultContactSearchEmail)) {
				for (int j = 0; j < resultContactSearchEmail.size(); j++) {
					System.out.println(j +" - " + getContactDetails(resultContactSearchEmail.get(j)));
				}
				return resultContactSearchEmail;
			} else {
				System.out.println("No hay elementos para la búsqueda realizada. \n");
			}
			break;
		case "5":
			currentPhase = 0;
			return null;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			currentPhase = 2;
			break;
		}
		return null;
	}
	
	private void showContacts() {
		if (contactList.isEmpty()) System.out.println("No hay contactos - Utilice el menú 1 para crear un contacto\n");
		
		SimpleArrayList<Contact> result = contactList.inOrder();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(getContactDetails(result.get(i)));
		}
		
		currentPhase = 0;
	}
	
	private void loadDatabase() {
		Contact[] contacts = {
			    new Contact("Juan", "Pérez", 1123456789, "juan.perez@gmail.com"),
			    new Contact("María", "Gómez", 1134567890, "maria.gomez@gmail.com"),
			    new Contact("Lucas", "Fernández", 1145678901, "lucas.fernandez@gmail.com"),
			    new Contact("Sofía", "Martínez", 1156789012, "sofia.martinez@gmail.com"),
			    new Contact("Juan", "Rodríguez", 1167890123, "juan.rodriguez@gmail.com"),
			    new Contact("Valentina", "López", 1178901234, "valentina.lopez@gmail.com"),
			    new Contact("Martín", "Sánchez", 1189012345, "martin.sanchez@gmail.com"),
			    new Contact("Camila", "Ramírez", 1190123456, "camila.ramirez@gmail.com"),
			    new Contact("Lucas", "Torres", 1101234567, "lucas.torres@gmail.com"),
			    new Contact("Julieta", "Flores", 1112345678, "julieta.flores@gmail.com"),
			    new Contact("Agustín", "Acosta", 1122334455, "agustin.acosta@gmail.com"),
			    new Contact("María", "Herrera", 1133445566, "maria.herrera@gmail.com"),
			    new Contact("Franco", "Castro", 1144556677, "franco.castro@gmail.com"),
			    new Contact("Lucía", "Morales", 1155667788, "lucia.morales@gmail.com"),
			    new Contact("Juan", "Ortiz", 1166778899, "juan.ortiz@gmail.com")
		};
		for (Contact c : contacts) {
			contactList.insert(c);
		}
		System.out.println("Contactos Insertados Correctamente! \n");
		currentPhase = 0;
	}
	
	private String getContactDetails(Contact contact) {
		return String.join(" - ",(contact.getFirstName() + " " + contact.getLastName()),Integer.toString(contact.getCellphone()),contact.getEmail()); 
	}
	
	private Contact selectContact(SimpleArrayList<Contact> list) {
	    while(true) {
	        System.out.println("Seleccione el contacto por su ID: ");
	        int idInput = readInt();
	        if(idInput >= 0 && idInput < list.size()) {
	            return list.get(idInput);
	        }	
	        System.out.println("ID inválido.");
	    }
	}
	
	private boolean checkSearchResults(SimpleArrayList<Contact> list) {
		return !list.isEmpty();
	}
	
	private String validateEmail() {
	    while (true) {
	        String input = scanner.nextLine();
	        	Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input);
	            if (matcher.matches()) {
	            	return input;
	            }
	            else {
	            	System.out.println("Email no valido. Intenta nuevamente.");
	            }

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
	
	private void askInput(String method) {
		
		if (method == "delete") System.out.println("Desea remover otro elemento? (s/n)\n");
		else if (method == "add") System.out.println("Desea agregar otro elemento? (s/n)\n");
		
		String input = scanner.nextLine();
		
		switch(input) {
		case "s":
			if (method == "delete") {
				currentPhase = 2;
				break;
			}
			else currentPhase = 1;
			break;
		case "n":
			currentPhase = 0;
			break;
		default:
			System.out.println("Entrada Inválida, Intente Nuevamente");
			askInput(method);
			break;
		}
	}
}
