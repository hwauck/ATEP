package edu.gac.ATEP.server;

import java.util.ArrayList;
import java.util.List;



import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.gac.ATEP.shared.AssessmentTemplate;
import edu.gac.ATEP.shared.AssessmentTempStore;
import edu.gac.ATEP.shared.Category;
import edu.gac.ATEP.shared.Question;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


/**
 * The server side implementation of the RPC service.
 * @author max
 */

public class AssessmentTempStoreImpl extends RemoteServiceServlet implements 
	AssessmentTempStore {

		private static final long serialVersionUID = 7367373321119740703L;
//		private static final PersistenceManagerFactory pmf =
//				JDOHelper.getPersistenceManagerFactory("transactions-optional");
		

		@Override
		public void storeAssessmentTemplate(AssessmentTemplate aT) {
			PersistenceManager pm = StudentStoreImpl.pmf.getPersistenceManager();
			pm.makePersistent(aT);
		}

		@Override
		public List<AssessmentTemplate> getAssessmentTemplates(Long minimumID) {
			PersistenceManager pm = StudentStoreImpl.pmf.getPersistenceManager();
			Query query = pm.newQuery(AssessmentTemplate.class);
			query.declareParameters("Long minimumID");
			query.setFilter("id >= minimumID");
			query.setOrdering("id descending");

			@SuppressWarnings("unchecked")
			List<AssessmentTemplate> assessmentTemplates = new ArrayList<AssessmentTemplate>((List<AssessmentTemplate>) query.execute(minimumID));
			for(AssessmentTemplate aT : assessmentTemplates){
				ArrayList<Category> categories =
						new ArrayList<Category>(pm.detachCopyAll(aT.getCategories()));
				for(Category c : categories){
					ArrayList<Question> questions =
							new ArrayList<Question>(pm.detachCopyAll(c.getQuestions()));
					c.setMyQuestions(questions);
					c.setOwner(aT);
				}
				aT.setMyCategories(categories);
			}
			return assessmentTemplates;

		}
	

}
