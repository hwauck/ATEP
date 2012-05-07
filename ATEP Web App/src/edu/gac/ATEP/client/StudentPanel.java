package edu.gac.ATEP.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.Student;

public class StudentPanel extends VerticalPanel{
	
	private static Student stud;
	private static VerticalPanel panel1;
	private static VerticalPanel panel2;
	ArrayList<Assessment> assessments = stud.getMyAssessments();
	
	public StudentPanel(Student stud, VerticalPanel panel1, VerticalPanel panel2){
		super();
		this.stud = stud;
		this.panel1 = panel1;
		this.panel2 = panel2;
		initGUI();
		
	}

///////////////////////////////Create a handler for the ViewAssessmentButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	class ViewAssessmentHandler implements ClickHandler{
		
		//Fired when the user clicks on the ViewAssessmentButton.
		public void onClick(ClickEvent event){
			panel1.setVisible(false);
			panel2.setVisible(true);
			VerticalPanel populatedPanel = new VerticalPanel();
		}

		//Send the name selected from the student list to the server and wait for a response.
		private void populatePanel() {
			
			
		}
	}
	
	private void initGUI() {
		this.add(new Label("Year in program: " + stud.getClassYear()));
		this.add(new Button("Delete this student"));
		this.add(new Label("Current Assessments: "));

		for (Assessment a : assessments){
			HorizontalPanel assessmentViewPanel = new HorizontalPanel();
			assessmentViewPanel.add(new Label(a.getName() + " -- Status: " + a.getStatus()));
			Button viewButton = new Button("View " + a.getName());
			assessmentViewPanel.add(viewButton);
			ViewAssessmentHandler viewAssessment = new ViewAssessmentHandler();
			viewButton.addClickHandler(viewAssessment);
			/*studentInfoPanel.add(assessmentInfoPanel);
			studentInfoPanel.add(assessmentViewPanel);
			j++;*/
		}
	}
}

