//package steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import net.serenitybdd.annotations.Managed;
//import pages.CoursePage;
//import pages.TrainersPage;
//
//public class TrainersStepdefs {
//
//    @Managed
//    CoursePage coursePage;
//
//    @Managed
//    TrainersPage trainersPage;
//
//
//
//    @Given("I am assigned to course {string} as a Trainer")
//    public void iAmAssignedToCourse(String courseName) {
//        coursePage.openCoursesPage();
//        trainersPage.assignToCourse(courseName);
//        trainersPage.acceptAlert();
//    }
//
//    @When("I assign myself to course {string}")
//    public void iAssignMyselfToCourse(String courseName) {
//        trainersPage.assignToCourse(courseName);
//    }
//
//    @When("I remove myself from course {string}")
//    public void iRemoveMyselfFromCourse(String courseName) {
//        trainersPage.removeAssignment(courseName);
//    }
//
//    @Then("I should see a trainer confirmation message")
//    public void iShouldSeeTrainerConfirmation() {
//        System.out.println(trainersPage.getAlertText());
//        trainersPage.acceptAlert();
//    }
//
//    @Then("I should see a trainer error message")
//    public void iShouldSeeTrainerError() {
//        System.out.println(trainersPage.getAlertText());
//        trainersPage.acceptAlert();
//    }
//}
