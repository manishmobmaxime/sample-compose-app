import com.compose.app.data.remote.services.AuthService
import com.compose.app.data.remote.services.AuthServiceImpl
import com.compose.app.data.remote.BaseService
import com.compose.app.data.remote.services.HomeService
import com.compose.app.data.remote.services.HomeServiceImpl
import org.koin.dsl.module

val networkModule = module {
    single { BaseService }
    single<AuthService> { AuthServiceImpl(get()) }
    single<HomeService> { HomeServiceImpl(get()) }
}
