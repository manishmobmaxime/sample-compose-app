import com.compose.app.data.remote.AuthService
import com.compose.app.data.remote.AuthServiceImpl
import com.compose.app.data.remote.BaseService
import org.koin.dsl.module

val networkModule = module {
    single { BaseService }
    single<AuthService> { AuthServiceImpl(get()) }
}
