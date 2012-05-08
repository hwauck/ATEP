package edu.gac.ATEP.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.Student;
import edu.gac.ATEP.shared.Category;
import edu.gac.ATEP.shared.Question;

public class StudentPanel extends VerticalPanel{
	
	private static Student stud;
	private static VerticalPanel panel1;
	private static VerticalPanel panel2;
	ArrayList<Assessment> assessments;
	
	public StudentPanel(Student stud, VerticalPanel panel1, VerticalPanel panel2){
		super();
		this.stud = stud;
		this.panel1 = panel1;
		this.panel2 = panel2;
		assessments = stud.getMyAssessments();
		initGUI();
		
	}

///////////////////////////////Create a handler for the ViewAssessmentButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	class ViewAssessmentHandler implements ClickHandler{
		private VerticalPanel populatedPanel = new VerticalPanel();
		private Assessment a1;
		
		//Fired when the user clicks on the ViewAssessmentButton.
		public void onClick(ClickEvent event){
			panel1.setVisible(false);
			panel2.setVisible(true);
			populatePanel(a1);
		}

		//Send the name selected from the student list to the server and wait for a response.
		private void populatePanel(Assessment assessmentToPopulate) {
			//create getCategories
			VerticalPanel viewAssessmentPanel = new VerticalPanel();
			ArrayList<Category> cats = assessmentToPopulate.getCategories();
			for (Category cat : cats){
				VerticalPanel catPanel = new VerticalPanel();
				populatedPanel.add(catPanel);
				ArrayList<Question> Qs = cat.getQuestions();
				for (Question q : Qs){
					VerticalPanel questionPanel = new VerticalPanel();
					HorizontalPanel scorePanel = new HorizontalPanel();
					Label questionLabel = new Label(q.getBodyText());
					CheckBox zero = new CheckBox("0");
					CheckBox one = new CheckBox("1");
					CheckBox two = new CheckBox("2");
					CheckBox three = new CheckBox("3");
					CheckBox four = new CheckBox("4");
					CheckBox five = new CheckBox("5");
					scorePanel.add(zero);
					scorePanel.add(one);
					scorePanel.add(two);
					scorePanel.add(three);
					scorePanel.add(four);
					scorePanel.add(five);
					questionPanel.add(questionLabel);
					questionPanel.add(scorePanel);
					catPanel.add(questionPanel);
				}
				viewAssessmentPanel.add(catPanel);
			}
			panel2.add(viewAssessmentPanel);
			
		}
	}
	
	private void initGUI() {
		this.add(new Label("Year in program: " + stud.getClassYear()));
		this.add(new Button("Delete this student"));
		this.add(new Label("Current Assessments: "));

		for (Assessment a : assessments){
			HorizontalPanel assessmentViewPanel = new HorizontalPanel();
			assessmentViewPanel.add(new Label(a.getName() + " -- Status: " + a.getStatus()));
			AssessmentButton viewButton = new AssessmentButton("View " + a.getName(), a);
			assessmentViewPanel.add(viewButton);
			ViewAssessmentHandler viewAssessment = new ViewAssessmentHandler();
			viewButton.addClickHandler(viewAssessment);
			/*studentInfoPanel.add(assessmentInfoPanel);
			studentInfoPanel.add(assessmentViewPanel);
			j++;*/
		}
	}
}

