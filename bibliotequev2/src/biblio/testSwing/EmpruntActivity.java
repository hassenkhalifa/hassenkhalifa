package biblio.testSwing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import biblio.testSwing.ConnectionActivity;
import Connection.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.Employe;
import biblio.domain.EmpruntEnCoursDB;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;
import biblio.exception.BiblioException;
import java.awt.Color;
import java.awt.EventQueue;

public class EmpruntActivity extends JFrame {
	/**
	 * @author hassenkhalifa
	 */
	ConnectionFactory cn = new ConnectionFactory();
	Connection connection = cn.getConnection("jdbc_biblio@localhost.properties");
	UtilisateursDao ud = new UtilisateursDao(connection);
	ExemplaireDao ed = new ExemplaireDao(connection);
	EmpruntEnCoursDao ec = new EmpruntEnCoursDao(connection);

	
	// JLIST 1
	JList<EmpruntEnCoursDB> list1 = new JList<>();
	DefaultListModel<EmpruntEnCoursDB> modelempruntencoursDB = new DefaultListModel<>();
	// JLIST 2
	JList<Exemplaire> list2 = new JList<>();
	DefaultListModel<Exemplaire> modelexemplaire = new DefaultListModel<>();
	// LISTS
	ArrayList<Exemplaire> exemplaires = new ArrayList<Exemplaire>(ed.findAllExemplaires());
	ArrayList<Exemplaire> exemplairesnondisponibles = new ArrayList<Exemplaire>(ed.findAllExemplairesPrete());
	ArrayList<EmpruntEnCoursDB> empruntencourslist = new ArrayList<EmpruntEnCoursDB>();
	// ATTRIBUTS
	static Integer KEY = ConnectionActivity.KEY;
	static String cat = ConnectionActivity.cat;
	String catemploye = "";
	Utilisateur user = new Utilisateur();
	private static int i = 0;
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtCompteDeLutilisateur;
	private JFrame empruntActivity;
	private JLabel txtListeDesExemplaires;
	EmpruntActivity() throws SQLException, BiblioException {
		if (cat.equals("ADHERENT")) {
			user = (Adherent) ud.findByKey(KEY);
		} else if (cat.equals("EMPLOYE")) {
			user = (Employe) ud.findByKey(KEY);
			Employe employe = (Employe) ud.findByKey(KEY);
			catemploye = String.valueOf(employe.getCategorieEmploye());
		}

		JFrame emprunter = new JFrame();
		emprunter.getContentPane().setBackground(new Color(255, 255, 255));
		emprunter.setTitle("Compte / Gestion des emprunts");
		emprunter.setBounds(100, 100, 775, 508);
		emprunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emprunter.getContentPane().setLayout(null);
		emprunter.setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Welcome");
		setSize(500, 500);
		
		// CHECKBOX
		JCheckBox chckbxPremier = new JCheckBox("Disponibles");
		chckbxPremier.setBackground(new Color(255, 255, 255));
		chckbxPremier.setSelected(true);
		buttonGroup.add(chckbxPremier);
		chckbxPremier.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxPremier.setBounds(174, 106, 97, 23);
		emprunter.getContentPane().add(chckbxPremier);
		JCheckBox chckbxSecond = new JCheckBox("Indisponibles");
		chckbxSecond.setBackground(new Color(255, 255, 255));
		chckbxSecond.setAutoscrolls(true);
		buttonGroup.add(chckbxSecond);
		chckbxSecond.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxSecond.setBounds(273, 106, 108, 23);
		emprunter.getContentPane().add(chckbxSecond);

		JLabel listexemp = new JLabel("Exemplaires");
		listexemp.setFont(new Font("Arial", Font.BOLD, 13));
		@SuppressWarnings("unused")
		JScrollPane s = new JScrollPane(listexemp);
		listexemp.setBounds(42, 117, 97, 18);
		listexemp.setBounds(42, 117, 97, 18);

		/** METHODES CHECKBOX1 AFFICHE LES EXEMPLAIRES DISPONIBLES **/
		chckbxPremier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				list2.setModel(modelexemplaire);
			}
		});

		/** METHODES CHECKBOX2 AFFICHE LES EXEMPLAIRES INDISPONIBLES **/
		chckbxSecond.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JCheckBox cb = (JCheckBox) event.getSource();
				if (cb.isSelected()) {
					list2.setModel(
							new DefaultComboBoxModel(new ArrayList<Exemplaire>(ed.findAllExemplairesPrete()).toArray()));
				}
			}
		});

		/** AJOUT DES EMPRUNTS EN COURS DATABASE A L'OBJET USER ET AU MODEL **/
		empruntencourslist = ec.findAllByUtilisateur(KEY);
		for (EmpruntEnCoursDB empruntencourslist : this.empruntencourslist) {
			modelempruntencoursDB.addElement(empruntencourslist);
		}

		for (Exemplaire exemplaires : this.exemplaires) {
			modelexemplaire.addElement(exemplaires);
		}
		for (EmpruntEnCoursDB empruntencourslist : this.empruntencourslist) {
			user.addEmpruntEnCours(empruntencourslist);
		}

		/** JLIST1 AFFICHE LES EMPRUNTS EN COURS DE L'UTILISATEUR **/
		list1 = new JList<EmpruntEnCoursDB>(modelempruntencoursDB);
		list1.setBorder(new LineBorder(new Color(0, 128, 128)));
		list1.setModel(modelempruntencoursDB);
		list1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		list1.setBackground(Color.WHITE);
		list1.setBounds(402, 136, 327, 224);
		emprunter.getContentPane().add(list1);

		/** JLIST2 AFFICHE LES EXEMPLAIRES DISPONIBLES ET NON DISPONIBLES **/
		list2 = new JList<Exemplaire>(modelexemplaire);
		list2.setModel(modelexemplaire);
		list2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		list2.setBackground(Color.WHITE);
		list2.setBounds(35, 136, 346, 224);
		list2.setBorder(new LineBorder(new Color(0, 128, 128)));
		emprunter.getContentPane().add(list2);

		txtListeDesExemplaires = new JLabel();
		txtListeDesExemplaires.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtListeDesExemplaires.setBackground(Color.WHITE);
		txtListeDesExemplaires.setFocusable(false);
		txtListeDesExemplaires.setText("Liste des Exemplaires");
		txtListeDesExemplaires.setBounds(35, 105, 121, 25);
		emprunter.getContentPane().add(txtListeDesExemplaires);

		// BUTTON RENDRE
		JButton Rendre = new JButton("Rendre");
		Rendre.setForeground(new Color(255, 255, 255));
		Rendre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Rendre.setBackground(new Color(0, 153, 102));
		Rendre.setBounds(501, 371, 142, 53);
		emprunter.getContentPane().add(Rendre);
		/**
		 * RENDRE UN EXEMPLAIRE + SUPPRIMER LEMPRUNT DE LUTILISATEUR + ARCHIVER LEMPRUNT
		 **/
		Rendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (empruntencourslist.size() > 0) {
					i = list1.getSelectedIndex();
					EmpruntEnCoursDB d = (EmpruntEnCoursDB) user.getEmpruntEnCours().get(i);
					user.getEmpruntEnCours().remove(i);

					try {
						ec.deleteEmpruntEnCours(d.getIdExemplaire());
					} catch (SQLException | BiblioException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// GESTION DE LA LISTE DEXEMPLAIRES
					Exemplaire a = null;
					try {
						a = ed.findByKey(d.getIdExemplaire());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					modelexemplaire.addElement(a);
					exemplaires.add(a);
					// GESTION DE LA LISTE DES EMPRUNTS EN COURS
					empruntencourslist.remove(i);
					modelempruntencoursDB.remove(i);
				}
			}
		});

		/** ENREGISTRER UN EMPRUNT **/
		JButton btnEmprunter = new JButton("Emprunter");
		btnEmprunter.setForeground(new Color(255, 255, 255));
		btnEmprunter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEmprunter.setBackground(new Color(0, 153, 102));
		btnEmprunter.setBounds(142, 371, 145, 49);
		emprunter.getContentPane().add(btnEmprunter);

		btnEmprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPremier.isSelected()) {
					if (cat.equals("ADHERENT") && exemplaires.size() > 0) {
						try {
							@SuppressWarnings("unused")
							Exemplaire exemp = new Exemplaire();
							i = list2.getSelectedIndex();
							System.out.println(i);
							Exemplaire a = exemplaires.get(i);
							EmpruntEnCoursDB d = new EmpruntEnCoursDB(user.getIdUtilisateur(), a.getIdExemplaire(),
									LocalDate.now());

							if (user.isConditionsPretAcceptees()) {
								user.addEmpruntEnCours(d);

								// GESTION DE LA LISTE DEXEMPLAIRES
								modelexemplaire.remove(i);
								exemplaires.remove(i);
								// GESTION DE LA LISTE DES EMPRUNTS EN COURS
								modelempruntencoursDB.addElement(d);
								empruntencourslist.add(d);
								// INSERTION DE L'EMPRUNT DANS LA BASE DE DONNEE
								ec.insertEmpruntEnCours(d);
							}
						} catch (BiblioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (cat.equals("EMPLOYE") && exemplaires.size() > 0) {
						try {
							i = list2.getSelectedIndex();
							Exemplaire a = exemplaires.get(i);
							EmpruntEnCoursDB d = new EmpruntEnCoursDB(user.getIdUtilisateur(), a.getIdExemplaire(),
									LocalDate.now());

							if (user.isConditionsPretAcceptees()) {
								user.addEmpruntEnCours(d);
								// GESTION DE LA LISTE DEXEMPLAIRES
								modelexemplaire.remove(i);
								exemplaires.remove(i);
								// GESTION DE LA LISTE DES EMPRUNTS EN COURS
								modelempruntencoursDB.addElement(d);
								empruntencourslist.add(d);
								// INSERTION DE L'EMPRUNT DANS LA BASE DE DONNEE
								ec.insertEmpruntEnCours(d);
							}

						} catch (BiblioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		txtCompteDeLutilisateur = new JTextField();
		txtCompteDeLutilisateur.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtCompteDeLutilisateur.setBackground(Color.WHITE);
		txtCompteDeLutilisateur
				.setText("Compte n°" + KEY + " " + "Type : " + " " + ud.findcatbykey(KEY) + " " + catemploye);
		txtCompteDeLutilisateur.setEditable(false);
		txtCompteDeLutilisateur.setBounds(218, 36, 302, 23);
		emprunter.getContentPane().add(txtCompteDeLutilisateur);
		txtCompteDeLutilisateur.setColumns(10);

		// BUTTON BACK
		JButton back = new JButton(new ImageIcon(
				((new ImageIcon("back.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		back.setBounds(10, 11, 30, 30);
		emprunter.getContentPane().add(back);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try {
					new ConnectionActivity();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				emprunter.dispose();
			}
		});

		
		
	}
	

	
	public static void main(String[] args) throws SQLException, BiblioException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpruntActivity window = new EmpruntActivity();
					window.empruntActivity.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
