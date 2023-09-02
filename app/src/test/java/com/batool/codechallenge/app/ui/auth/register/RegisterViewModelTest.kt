import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.batool.codechallenge.app.ui.auth.register.RegisterViewModel
import com.batool.codechallenge.app.util.isValidEmail
import com.batool.codechallenge.app.util.resource.ResourceProvider
import com.batool.codechallenge.app.util.validatePhoneNumber
import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.domain.usecases.GeneralUseCases
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RegisterViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Mock
    private lateinit var generalUseCases: GeneralUseCases

    @Inject
    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = RegisterViewModel(generalUseCases)
    }

    @Test
    fun `test createAccount with valid fields`() {
        // Arrange
        viewModel.name.value = "Batool"
        viewModel.id.value = "123"
        viewModel.email.value = "Batool@gmail.com" // Valid email format
        viewModel.phone.value = "1234567890"
        viewModel.dob.value = "01/01/1990"
        viewModel.password.value = "Password123"
        viewModel.confirmPassword.value = "Password123"

        val expectedUser = User(
            "123",
            "Batool",
            "Batool@gmail.com",
            "1234567890",
            "01/01/1990"
        )

        // Act
        viewModel.createAccount()

        // Assert
        assert(viewModel.createAccountSuccess.value)
        Mockito.verify(generalUseCases).saveUser(expectedUser)
    }

    @Test
    fun `test createAccount with invalid fields`() {
        // Arrange
        viewModel.name.value = ""
        viewModel.id.value = ""
        viewModel.email.value = "invalid-email"
        viewModel.phone.value = "12345"
        viewModel.dob.value = ""
        viewModel.password.value = "weak"
        viewModel.confirmPassword.value = "password"

        // Act
        viewModel.createAccount()

        // Assert
        assert(!viewModel.createAccountSuccess.value)
        assert(viewModel.nameError.value?.isNotEmpty() == true)
        assert(viewModel.idError.value?.isNotEmpty() == true)
        assert(!viewModel.email.value.isValidEmail())
        assert(viewModel.phoneError.value?.isNotEmpty() == true)
        assert(viewModel.dobError.value?.isNotEmpty() == true)
        assert(viewModel.passwordError.value?.isNotEmpty() == true)
        assert(viewModel.confirmPasswordError.value?.isNotEmpty() == true)
        Mockito.verify(generalUseCases, Mockito.never()).saveUser(Mockito.any())
    }

    @Test
    fun `test setDateOfBirth`() {
        // Arrange
        val viewDate = "01/01/1990"
        val date = "1990-01-01"

        // Act
        viewModel.setDateOfBirth(viewDate, date)

        // Assert
        assert(viewModel.dob.value == viewDate)
        assert(viewModel.date == date)
    }

    @Test
    fun `test validatePhoneNumber`() {
        // Arrange
        val validPhoneNumber = "1234567890"
        val invalidPhoneNumber = "12345"

        // Act
        val isValidValidPhoneNumber =
            resourceProvider.provideAppContext().validatePhoneNumber(validPhoneNumber)
        val isValidInvalidPhoneNumber =
            resourceProvider.provideAppContext().validatePhoneNumber(invalidPhoneNumber)

        // Assert
        assert(isValidValidPhoneNumber)
        assert(!isValidInvalidPhoneNumber)
    }
}
