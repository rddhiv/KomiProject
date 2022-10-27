package StepDefinitions;
import com.cucumberFramework.pageObjects.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;



public class KomiWebpage {
	WebDriver driver = null;
	KomiWebpageFunctions kwb = new KomiWebpageFunctions(driver);

	
	
	@Given("^User Open the Webpage \"([^\"]*)\"$")
	public void user_click_on_the_webpage(String arg1) {
		kwb.OpenWebpage(arg1);
		}

	@Then("User checks if all the thumbnail images are loaded and visible")
	public void user_checks_if_all_the_thumbnail_images_are_loaded_and_visible() {
		kwb.verifyThumnailsVerification();
		
	}
	@And("Clicking on the section title at the top takes you to the right module")
	public void clicking_on_the_section_title_at_the_top_takes_you_to_the_right_module() {
		kwb.verifySectionTitle();
	}
	@Then("User clicks on the side navigation arrow on the Music Tracks module and validates the paging and data.")
	public void user_clicks_on_the_side_navigation_arrow_on_the_music_tracks_module_and_validates_the_paging_and_data() {
		kwb.verifyPageNavigationMusicTracks();
	}
	@Then("User clicks on “Pre-Save” button and validates the button changes to “Pre-saved”")
	public void user_clicks_on_pre_save_button_and_validates_the_button_changes_to_pre_saved() {
		kwb.verifyPresaveButton();
	}
	@Then("User clicks on a Youtube video and asserts that it redirects successfully")
	public void user_clicks_on_a_youtube_video_and_asserts_that_it_redirects_successfully() {
		kwb.verifyYoutubeButton();
	}
	@When("Clicks on the More button in the Single Music module validates and redirects to all relevant links")
	public void clicks_on_the_more_button_in_the_single_music_module_validates_and_redirects_to_all_relevant_links() {
		kwb.verifySinglemusicModule();
	}
	@Then("User clicks on a Subscription module and fills the form {string},{string} successfully")
	public void user_clicks_on_a_subscription_module_and_fills_the_form_successfully(String name, String email) {
		kwb.verifySubscriptionModule(name,email);
	}
	
	@And("User close the webpage")
	public void User_close_the_webpage()
	{
	kwb.closebrowser();
	}
	
}
