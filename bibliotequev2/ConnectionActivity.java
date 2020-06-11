package biblio.testSwing;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Connection.ConnectionFactory;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.Employe;
import biblio.exception.BiblioException;

import javax.swing.JButton;
import biblio.testSwing.EmpruntActivity; 
public class main ConnectionActivity {

	private Connection connection;
	private JFrame connectionActivity;
	private JFrame EmpruntActivity;
	private JTextField textField;
	static Integer KEY;
	static String cat;
	ConnectionFactory  cn = new ConnectionFactory();
	UtilisateursDao ud = new UtilisateursDao(cn.getConnection("jdbc_biblio@localhost.properties"));	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
					ConnectionActivity window = new ConnectionActivity();					
					window.connectionActivity.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ConnectionActivity() throws UnknownHostException {
		
		
		connectionActivity = new JFrame();
		connectionActivity.setTitle("Bibliothèque / Se Connecter");
		connectionActivity.setBounds(100, 100, 450, 300);
		connectionActivity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connectionActivity.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Entrez l'Identifiant Utilisateur");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 56, 180, 18);
		connectionActivity.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(233, 53, 191, 21);
		connectionActivity.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(20, 126, 113, 41);
		connectionActivity.getContentPane().add(btnAnnuler);
		
		
		// BOUTON CONNECTION
		JButton btnOk = new JButton("Se connecter");		
		btnOk.setBounds(260, 126, 126, 41);
		connectionActivity.getContentPane().add(btnOk);
        btnOk.addActionListener(new ActionListener() 
        {
         public  void    actionPerformed (ActionEvent e)
         {
        	String i1 = String.valueOf(textField.getText());         	
        	Boolean s = isNumeric(String.valueOf(textField.getText()));        	
        	if(s)
        	{
        		 KEY = Integer.valueOf(textField.getText());
        		cat = ud.findcatbykey(KEY); 
        		if (cat.equals("ADHERENT")) {
        		Adherent a1 = (Adherent) ud.findByKey(KEY);    		
        		   try {
					new EmpruntActivity();					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BiblioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	   		connectionActivity.dispose();
        		}        		
        		else if (cat.equals("EMPLOYE")) {
        			Employe employe = (Employe) ud.findByKey(KEY);        			
        			  try {        				  
						new EmpruntActivity();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BiblioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
          	   		connectionActivity.dispose(); 
          	   	
        		}      
        	/*	else if (cat.equals("GESTIONNAIRE")) {
        			//cat  ="EMPLOYE";    			Employe employe = (Employe) ud.findByKey(KEY);
       			try {  new EmpruntActivity();} catch (SQLException e1) {
						// TODO Auto-generated catch blocke1.printStackTrace();}
          	   		connectionActivity.dispose();	}  */
        		else {
    				JOptionPane.showMessageDialog(null, "Utilisateur inconnu");	        		}
        	    }
        	else {
				JOptionPane.showMessageDialog(null, "Saisir un numérique");	        		} 
        }        
        } );          
        
        btnAnnuler.addActionListener(new ActionListener() 
        {
         public  void    actionPerformed (ActionEvent e)
         {
        	 Runtime.getRuntime().exit(0)      ;        	 
        }        
        } );        
        
	}	
	

	   public static boolean isNumeric(final String str) {	       
	        if (str == null || str.length() == 0) {
	            return false;	        }
	        for (char c : str.toCharArray()) {
	            if (!Character.isDigit(c)) {
	                return false;
	            }	        }
	        return true;
	    }
}
