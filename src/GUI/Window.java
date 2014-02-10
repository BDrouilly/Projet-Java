/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import JobPackage.*;

import java.sql.*;

import javax.swing.GroupLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Alexis
 */
public class Window extends javax.swing.JFrame {

    /**
     * Creates new form Window
     */
    private ArrayList<JMenuItem> MapList;
    private MappingMap mapping;
    private MappingPoi mappingPoi;
    private MappingRoute mappingRoute;
    private int i;
    private float newPoiX;
    private float newPoiY;
    private int currMapId;
    private Poi selectedPoi;
    private String[] routesName;
    private int currRouteId;
    
    private ArrayList<Route> Routes;
    private ArrayList<Map> Maps;
    private ArrayList<Poi> Pois;
    
   //private Graphics g = null;
    
    public Window() {
        this.mappingRoute = new MappingRoute();
        initComponents();
        Maps = new ArrayList<Map>();
        Pois = new ArrayList<Poi>();
        Routes = new ArrayList<Route>();
        mapping = new MappingMap(); // objet MappingMap
        mappingPoi = new MappingPoi();
       // Names = new ArrayList<String>();
       // IDs = new ArrayList<Integer>();
        MapList = new ArrayList<JMenuItem>();
        this.makeListOfMaps();
        this.showListOfMapsInMenuBar();
        this.Map_Panel.addMouseListener(new MouseListenerMap());
        this.Button_Add.addActionListener(new ActionListenerAddPOI());
        this.Button_AddPOI.addActionListener(new ActionListenerAddButton());
        this.Button_DelPOI.addActionListener(new ActionListenerDelButton());
        this.Button_Prev.addMouseListener(new MouseListenerPrevButton());
        this.Button_Next.addMouseListener(new MouseListenerNextButton());
        this.setSize(800,600);
        this.setResizable(false);
        this.newPoiX = -1;
        this.newPoiY = -1;
        
    }
    public void makeListOfRoutes() {
    	int i = 0;
    	try {
    		ResultSet rs = this.mappingRoute.getRouteByMapId(currMapId);
    		while(rs.next()){
    			this.Routes.add(new Route(rs.getInt("ROUTE_ID")));
    			i++;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(i > 0) {
	    	this.routesName = new String[i + 1];
	    	this.routesName[0] = "Carte sans itin�raire";
 	    	
	    	for(int j = 0; j < i; j++){
	    		routesName[j+1] = Routes.get(j).getLabel();
	    	}
    	} else {
    		this.routesName = new String[1];
    		this.routesName[0] = "Aucun itin�raire sur cette carte";
    	}
    	Combo_Interface.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(Combo_Interface.getSelectedIndex() != 0){
					pimpMyPois(Combo_Interface.getSelectedItem().toString());
				} else {
					resetPoiToFull();
				}
			}
    		
    	});
    }
    public void resetPoiToFull(){
    	ResultSet rs = mappingPoi.getPoiByMapId(currMapId);
    	try {
			while(rs.next()){
                Pois.add(new Poi(rs.getInt("POI_ID")));
			}
			Map_Panel.setPois(Pois);
            Map_Panel.revalidate();
            Map_Panel.repaint();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.currRouteId = 0;
    }
    public void pimpMyPois(String routeName){
    	ResultSet rs = mappingRoute.getRouteByName(routeName);
        Pois.clear();
    	try {
    		rs.next();
    		this.currRouteId = rs.getInt("ROUTE_ID");
			rs = mappingPoi.getPoiByRoute(this.currRouteId);
           try{
               while(rs.next())
               {
                   Pois.add(new Poi(rs.getInt("POI_ID")));
               }
               Map_Panel.setPois(Pois);
               Map_Panel.revalidate();
               Map_Panel.repaint();
               selectedPoi = Pois.get(0);
               ShowInfoPoi();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (Exception e){
        	e.printStackTrace();
        }    	
    }
    class ActionListenerAddPOI implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if ((Write_Info1.getText() != "") || (Write_Info2.getText() != "") || (Write_Info3.getText() != "") &&
					(Wrtie_Address.getText() != "") || (Write_Nom.getText() != "") || ((jTextArea2.getText() != "")) && (jTextArea2.getText() != "Description")){
				if ((newPoiX < 0) || (newPoiY < 0)) {
					JOptionPane.showMessageDialog(Add_Panel, "Veuillez cliquer sur la carte pour d�finir les coordonn�es du POI !");
				} else {
					mappingPoi.setNew(Write_Nom.getText(), newPoiX, newPoiY, Write_Info1.getText(), jTextArea2.getText(), Wrtie_Address.getText(), currMapId);
                    Pois.add(new Poi(Write_Nom.getText(), Write_Info1.getText(), jTextArea2.getText(), Write_Info2.getText(), (int)newPoiX, (int)newPoiY, currMapId));
					Map_Panel.setPois(Pois);
					Map_Panel.revalidate();
					Map_Panel.repaint();
					Write_Info1.setText("");
					Write_Info2.setText("");
					Write_Info3.setText("");
					Write_Nom.setText("");
					Wrtie_Address.setText("");
					jTextArea2.setText("");
					JOptionPane.showMessageDialog(Map_Panel, "POI Ajout� !");
				}
			} else {
				JOptionPane.showMessageDialog(Add_Panel, "Veuillez remplir les champs requis");
			}
		}    	
    }
    class ActionListenerDelButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(JOptionPane.showConfirmDialog(Map_Panel, "Supprimer ce POI ? (D�finitif)") == 0){
				mappingPoi.deletePoi(selectedPoi.getId());
				Pois.remove(selectedPoi);
				showVoidPoiInfo();
				Map_Panel.setPois(Pois);
				Map_Panel.revalidate();
				Map_Panel.repaint();
				refreshInterface();
			}
			
		}
    	
    }
    class ActionListenerAddButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			newPoiX = -1;
			newPoiY = -1;
			Add_Panel.setSelectedIndex(1);	
		}    	
    }
    class MouseListenerMap implements MouseListener{

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		// TODO Auto-generated method stub

                
    	}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
    		newPoiX = (e.getX() * 100) / (float)Map_Panel.getWidth();
    		newPoiY = (e.getY() * 100) / (float)Map_Panel.getHeight();                
                for( Poi poi : Pois)
                {
                    if ((Math.abs(poi.getCoordX()-newPoiX) < 5 ) && (Math.abs(poi.getCoordY()-newPoiY) < 10 ))
                    {
                    	System.out.println("-- hop hopon va a l'oglet 0 ?" + (Math.abs(poi.getCoordX()-(int)newPoiX)) +" "+  (Math.abs(poi.getCoordY()-(int)newPoiY)));
                        selectedPoi = poi;
                        ShowInfoPoi();
                    	Add_Panel.setSelectedIndex(0);
                    	break;
                    } else {
                    	System.out.println("-- hop hopon va a l'oglet 1 ?" + (Math.abs(poi.getCoordX()-(int)newPoiX)) +" "+  (Math.abs(poi.getCoordY()-(int)newPoiY)));
                    	Add_Panel.setSelectedIndex(1);
                    }
                }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    
     class MouseListenerNextButton implements MouseListener{

    	@Override
    	public void mouseClicked(MouseEvent e) {
            System.out.println("yo, selected POI:"+Pois.indexOf(selectedPoi));
    		 if(Pois.indexOf(selectedPoi)-1 >= 0)
                 {
                        selectedPoi = Pois.get(Pois.indexOf(selectedPoi)-1);
                        ShowInfoPoi();
                 }
                 System.out.println("yo, selected POI:"+Pois.indexOf(selectedPoi));
    	}
        
        @Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
                
        @Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
                
                @Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
                
                @Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
                }
    	
    }
     
     class MouseListenerPrevButton implements MouseListener{

    	@Override
    	public void mouseClicked(MouseEvent e) {
            System.out.println("yo, selected POI:"+Pois.indexOf(selectedPoi));
    		 if(Pois.indexOf(selectedPoi)+1 < Pois.size())
                 {
                        selectedPoi = Pois.get(Pois.indexOf(selectedPoi)+1);
                        ShowInfoPoi();
                 }
                 System.out.println("yo, selected POI:"+Pois.indexOf(selectedPoi));
    	}
        
        @Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
                
        @Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
                
                @Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
                
                @Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
                }
    	
    }
    
   public void ShowInfoPoi()
    {   
        if (selectedPoi != null)
        {
        Info_1.setText(selectedPoi.getName());
        Info_2.setText(selectedPoi.getLabel());
        Info_3.setText(selectedPoi.getText());
        Info_4.setText(selectedPoi.getLink());
        Text_Information.setText(selectedPoi.getText());
        Map_Panel.setSelected(selectedPoi);
        Map_Panel.revalidate();
	Map_Panel.repaint();
        }
    }
    public void showVoidPoiInfo()
    {
        Info_1.setText("Aucun POI s�lectionn�");
        Info_2.setText("");
        Info_3.setText("");
        Info_4.setText("");
    }
    public void makeListOfMaps() { // RECUPERE LES INFOS SUR LES MAPS ET REMPLIE LES TABLEAUX
        ResultSet result =  mapping.getMap();
        try {

            while(result.next()) {
               Maps.add(new Map(result.getInt("MAP_ID")));
             }

         } catch (Exception e) { System.out.println("makeListOfMaps a chié"); e.printStackTrace();}
    }
    
    public void showListOfMapsInMenuBar() {
         

        try {
        for(i = 0; i < Maps.size(); i++) {
            MapList.add(new JMenuItem(Maps.get(i).getName()));
            Menu_Lieux.add(MapList.get(i));
            MapList.get(i).addActionListener(action);
        }
        } catch(Exception e) {e.printStackTrace();}
        
        
    }
    
        java.awt.event.ActionListener action = new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
           // e.getSource() permet de connaître la source qui a déclenché l'action
            System.out.println(e.getActionCommand());
            int poiGetted = 0;
             for(i = 0; i < MapList.size(); i++) {
               // System.out.println(MapList.get(i).getText() + "//" + Maps.get(i).getName());
                if( Maps.get(i).getName() == e.getActionCommand()) {
                    Pois.clear();
                    currMapId = Maps.get(i).getID();
                    System.out.print("TRALALALALLA");      
                    Map_Panel.setURL(Maps.get(i).getURL());
                    makeListOfRoutes();
                    Combo_Interface.setModel(new javax.swing.DefaultComboBoxModel(routesName));
                    ResultSet rs = mappingPoi.getPoiByMapId(Maps.get(i).getID());
                    if(rs != null){
	                   try{
		                   while(rs.next())
		                   {
		                	   poiGetted++;
		                       Pois.add(new Poi(rs.getInt("POI_ID")));
		                   }
		                   Map_Panel.setPois(Pois);
		                   } catch(Exception ex) {
		                	   	ex.printStackTrace();
		                   }
	                   if(poiGetted > 0) {
		                    selectedPoi = Pois.get(0);
		                    ShowInfoPoi();
	                   } else {
	                	   showVoidPoiInfo();
	                   }
	           }
		                    Map_Panel.revalidate();
		                    Map_Panel.repaint();
		                    break;                
	            }
            }
            
        }
     };
    public void refreshInterface(){
    	/**
    	 * TODO : add a clean refresh method
    	 */
        makeListOfRoutes();
        Combo_Interface.setModel(new javax.swing.DefaultComboBoxModel(routesName));        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Map_Panel = new Panel_Picture();
        Research_Panel = new javax.swing.JPanel();
        SearchBar = new javax.swing.JTextField();
        Scroll_Research = new javax.swing.JScrollPane();
        Result_Research = new javax.swing.JTextArea();
        Navigation_Panel = new javax.swing.JPanel();
        Combo_Interface = new javax.swing.JComboBox();
        Button_AddPOI = new javax.swing.JButton();
        Button_DelPOI = new javax.swing.JButton();
        Button_Prev = new javax.swing.JButton();
        Button_Next = new javax.swing.JButton();
        Add_Panel = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        Consult_Panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Text_Information = new javax.swing.JTextArea();
        Info_1 = new javax.swing.JLabel();
        Info_2 = new javax.swing.JLabel();
        Info_3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Info_4 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        Write_Nom = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Wrtie_Address = new javax.swing.JTextField();
        Write_Info1 = new javax.swing.JTextField();
        Write_Info2 = new javax.swing.JTextField();
        Write_Info3 = new javax.swing.JTextField();
        Button_Add = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        Menu_Lieux = new javax.swing.JMenu();
        Menu_POI = new javax.swing.JMenu();
        Menu_Help = new javax.swing.JMenu();
        newRoutePanel = new javax.swing.JPanel();
        editRoutePanel = new javax.swing.JPanel();
        
        newRoutePanel.setLayout(new java.awt.BorderLayout());
        newRoutePanel.add(editRoutePanel, BorderLayout.SOUTH);
        newRoutePanel.add(new javax.swing.JLabel("Ajouter le POI actuel � l'itineraire selectionn�"), BorderLayout.SOUTH);

        editRoutePanel.setLayout(new BorderLayout());
        editRoutePanel.add(new javax.swing.JButton("Ajouter"), BorderLayout.SOUTH);
        
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));

        Map_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout Map_PanelLayout = new javax.swing.GroupLayout(Map_Panel);
        Map_Panel.setLayout(Map_PanelLayout);
        Map_PanelLayout.setHorizontalGroup(
            Map_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );
        Map_PanelLayout.setVerticalGroup(
            Map_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Research_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        SearchBar.setText("Recherche");

        Result_Research.setColumns(20);
        Result_Research.setRows(5);
        Scroll_Research.setViewportView(Result_Research);

        javax.swing.GroupLayout Research_PanelLayout = new javax.swing.GroupLayout(Research_Panel);
        Research_Panel.setLayout(Research_PanelLayout);
        Research_PanelLayout.setHorizontalGroup(
            Research_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Research_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Research_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchBar)
                    .addComponent(Scroll_Research))
                .addContainerGap())
        );
        Research_PanelLayout.setVerticalGroup(
            Research_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Research_PanelLayout.createSequentialGroup()
                .addComponent(SearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_Research))
        );

        Navigation_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Combo_Interface.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Selectioner une carte"}));

        Button_AddPOI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Resources/addmark.PNG")));
        Button_AddPOI.setBorderPainted(false);
        Button_AddPOI.setContentAreaFilled(false);
        Button_AddPOI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_AddPOIActionPerformed(evt);
            }
        });

        Button_DelPOI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Resources/delmark.PNG")));
        Button_DelPOI.setBorderPainted(false);
        Button_DelPOI.setContentAreaFilled(false);

        Button_Prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Resources/fleche_suivant.png")));
        Button_Prev.setBorderPainted(false);
        Button_Prev.setContentAreaFilled(false);

        Button_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Resources/fleche_precedent.png")));
        Button_Next.setBorderPainted(false);
        Button_Next.setContentAreaFilled(false);

        javax.swing.GroupLayout Navigation_PanelLayout = new javax.swing.GroupLayout(Navigation_Panel);
        Navigation_Panel.setLayout(Navigation_PanelLayout);
        Navigation_PanelLayout.setHorizontalGroup(
            Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Combo_Interface, 0, 297, Short.MAX_VALUE)
                    .addGroup(Navigation_PanelLayout.createSequentialGroup()
                        .addComponent(Button_AddPOI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_DelPOI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_Prev, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Navigation_PanelLayout.setVerticalGroup(
            Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_PanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Prev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_Next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_AddPOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_DelPOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Combo_Interface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        Text_Information.setColumns(20);
        Text_Information.setRows(5);
        jScrollPane2.setViewportView(Text_Information);

        Info_1.setText("Info_1");

        Info_2.setText("Info_2");

        Info_3.setText("Info_3");

        jLabel4.setText("Information POI");

        Info_4.setText("Info_4");

        javax.swing.GroupLayout Consult_PanelLayout = new javax.swing.GroupLayout(Consult_Panel);
        Consult_Panel.setLayout(Consult_PanelLayout);
        Consult_PanelLayout.setHorizontalGroup(
            Consult_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Consult_PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Consult_PanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(Consult_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Info_4)
                    .addComponent(Info_3)
                    .addComponent(Info_2)
                    .addComponent(Info_1))
                .addGap(68, 68, 68))
            .addGroup(Consult_PanelLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Consult_PanelLayout.setVerticalGroup(
            Consult_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Consult_PanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(Info_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Info_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Info_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Info_4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jToolBar1.add(Consult_Panel);

        Add_Panel.addTab("Consulter", jToolBar1);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jLabel1.setText("Nom");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Description");
        jScrollPane3.setViewportView(jTextArea2);

        jLabel2.setText("Adresse");

        jLabel3.setText("Info 1");

        jLabel5.setText("Info 2");

        jLabel6.setText("Info 3");

        Button_Add.setText("Ajouter");
        Button_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Write_Nom, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(Wrtie_Address)
                    .addComponent(Write_Info1)
                    .addComponent(Write_Info2)
                    .addComponent(Write_Info3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(Button_Add)
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Write_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Wrtie_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Write_Info1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Write_Info2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Write_Info3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Add)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar2.add(jPanel2);

        Add_Panel.addTab("Ajouter", jToolBar2);
        Add_Panel.addTab("Itin�raires", (currRouteId == 0)? newRoutePanel : editRoutePanel);

        
        Menu_Lieux.setText("Lieux");
        Menu_Lieux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Menu_LieuxActionPerformed(evt);
            }
        });
        MenuBar.add(Menu_Lieux);

        Menu_POI.setText("POI");
        MenuBar.add(Menu_POI);

        Menu_Help.setText("Help");
        MenuBar.add(Menu_Help);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Map_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Navigation_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Add_Panel)
                    .addComponent(Research_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Map_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Add_Panel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Navigation_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Research_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_AddPOIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_AddPOIActionPerformed
        // TODO add your handling code here:
      //  Information_Panel.setVisible(false);

    }//GEN-LAST:event_Button_AddPOIActionPerformed

    private void Button_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_AddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_AddActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Add_Panel;
    private javax.swing.JButton Button_Add;
    private javax.swing.JButton Button_AddPOI;
    private javax.swing.JButton Button_DelPOI;
    private javax.swing.JButton Button_Next;
    private javax.swing.JButton Button_Prev;
    private javax.swing.JComboBox Combo_Interface;
    private javax.swing.JPanel Consult_Panel;
    private javax.swing.JPanel newRoutePanel;
    private javax.swing.JPanel editRoutePanel;
    private javax.swing.JLabel Info_1;
    private javax.swing.JLabel Info_2;
    private javax.swing.JLabel Info_3;
    private javax.swing.JLabel Info_4;
    private Panel_Picture Map_Panel;
    private javax.swing.JMenuBar MenuBar;
    public javax.swing.JMenu Menu_Help;
    public javax.swing.JMenu Menu_Lieux;
    public javax.swing.JMenu Menu_POI;
    private javax.swing.JPanel Navigation_Panel;
    private javax.swing.JPanel Research_Panel;
    private javax.swing.JTextArea Result_Research;
    private javax.swing.JScrollPane Scroll_Research;
    public javax.swing.JTextField SearchBar;
    private javax.swing.JTextArea Text_Information;
    private javax.swing.JTextField Write_Info1;
    private javax.swing.JTextField Write_Info2;
    private javax.swing.JTextField Write_Info3;
    private javax.swing.JTextField Write_Nom;
    private javax.swing.JTextField Wrtie_Address;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}



/*   CODE A AJOUTER SI VOUS REGLEZ LE PROBLEME DE CES PUTAINS D'EVENT DU DESIGNER DE MERDE

 private void Button_PrevMouseClicked(java.awt.event.MouseEvent evt) {    //poi précedent                                      
        if(Pois.get(Pois.indexOf(selectedPoi)-1) != null)
            selectedPoi = Pois.get(Pois.indexOf(selectedPoi)-1);
    }                                        

    private void Button_NextMouseClicked(java.awt.event.MouseEvent evt) { //poi suivant    
        if(Pois.get(Pois.indexOf(selectedPoi)+1) != null)
            selectedPoi = Pois.get(Pois.indexOf(selectedPoi)+1);
    }   


private void Button_RechercheMouseClicked(java.awt.event.MouseEvent evt) {  //recherche POI
        if( SearchBar.getText() != "" && currMapId > 0)
            mappingPoi.SearchPOI(SearchBar.getText(), currMapId);
    }             

*/