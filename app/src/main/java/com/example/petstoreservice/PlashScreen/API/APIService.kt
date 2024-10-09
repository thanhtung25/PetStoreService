import com.example.petstoreservice.PlashScreen.API.ApiResponse
import com.example.petstoreservice.PlashScreen.API.ApiResponsePet
import com.example.petstoreservice.PlashScreen.API.ApiResponseProduct
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface APIService {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResponse

    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(
        @Field("username") username: String,
        @Field("telephone") telephone: String,
        @Field("address") address: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ApiResponse

    @FormUrlEncoded
    @POST("addpet.php")
    suspend fun addPet(
        @Field("petname") petname: String,
        @Field("petbreed") petbreed: String,
        @Field("petbirthdate") petbirthdate: String,
        @Field("petweight") petweight: String,
        @Field("petgender") petgender: String,
        @Field("petnutrition") petnutrition: String,
        @Field("iduser") iduser: Int
    ): ApiResponsePet

    @GET("fetch_products.php")
    suspend fun fetchProducts(): ApiResponseProduct
}
object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.104/LoginRegister/"  // Thay địa chỉ server của bạn
    val instance: APIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(APIService::class.java)
    }
}