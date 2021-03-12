package edu.gitt.is.magiclibrary.view;


import javax.swing.JTextField;


import edu.gitt.is.magiclibrary.model.entities.Book;


import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.JLabel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class BookDetails extends TitleDetails {
	private static Logger log=Logger.getLogger(BookDetails.class.getName());
	

	
	private JTextField isbnField;
	private JTextField pagesField;
	private JLabel isbnLabel;
	private JLabel pagesLabel;
	



	


	/**
	 * <p>Creaci�n de la vista vac�a. Se usar� para introducir b�squedas o los datos de un libro nuevo</p>
	 */
	public BookDetails() {
		super();
		createPanel();
	}
	/**
	 * <p>Creaci�n de la vista con los datos de un libro. Se usar� para presentar los datos de un libro ya existente para su edici�n o eliminaci�n</p>
	 * @param book El libro a presentar en la vista
	 */
	public BookDetails(Book book) {
		
		createPanel();
		this.setEntity(book);
	}
	/**
	 * <p>Crea el panel a�adiendo los campos espec�ficos de un t�tulo de tipo libro, los campos gen�ricos de t�tulo est�n heredados de la vista de entidades de tipo t�tulo</p>
	 */
	private void createPanel() {
		log.info("Creando el panel de libro");
	
		
		isbnField = new JTextField();
		isbnField.setName("isbn");
		isbnField.setBounds(140, 166, 300, 20);
		add(isbnField);
		isbnField.setColumns(10);
	
		
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(33, 169, 46, 14);
		add(isbnLabel);
		
		pagesField = new JTextField();
		pagesField.setName("pages");
		pagesField.setBounds(140, 203, 300, 20);
		add(pagesField);
		pagesField.setColumns(10);
		
		pagesLabel = new JLabel("Pages");
		pagesLabel.setBounds(33, 206, 46, 14);
		add(pagesLabel);
	
		
		}


	/**
	 * <p>M�todo par establecer el libro en la vista, se mostrar�n sus datos por pantalla</p>
	 * @param book Libro que queremos mostrar por pantalla
	 */
	public void setEntity(Book book) {
		log.info("Mostrando atributos del libro");
		super.setEntity(book);
		isbnField.setText(book.getIsbn());		
		pagesField.setText(Integer.toString(book.getPages()));
	}
	/**
	 * 
	 * @return Devuelve un Book con los datos correspondientes a las casillas de texto de la vista
	 */
	
	public Book getBook() {
		log.info("Creando libro a partir de los datos de entrada");
		Book book=null;
		if (this.entity==null){
			try {
				book = new Book(nameField.getText(),authorField.getText(),MLView.getFrameManager().getDateFormat().parse(publishedAtField.getText()),isbnField.getText(),Integer.parseUnsignedInt(pagesField.getText()));
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
	
			//super.setTitle(book);
			setEntity(book);
		}else {
			entity.setAuthor(authorField.getText());
			entity.setName(nameField.getText());
			
			try {
				entity.setPublishedAt(MLView.getFrameManager().getDateFormat().parse(publishedAtField.getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			book=(Book) entity;
		}
		book.setIsbn(isbnField.getText());
		book.setPages(Integer.parseUnsignedInt(pagesField.getText()));
		
		return book;
	}
	
}