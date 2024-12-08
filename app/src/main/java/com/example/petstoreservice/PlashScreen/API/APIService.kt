
import com.example.petstoreservice.PlashScreen.Model.ApiResponse
import com.example.petstoreservice.PlashScreen.Model.ApiResponseCart
import com.example.petstoreservice.PlashScreen.Model.ApiResponsePet
import com.example.petstoreservice.PlashScreen.Model.ApiResponseProduct
import com.example.petstoreservice.PlashScreen.Model.ApiResponseWarehouse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {
    // api login
    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResponse
    // api register
    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(
        @Field("username") username: String,
        @Field("telephone") telephone: String,
        @Field("address") address: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ApiResponse
    // api add pet
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
    // api read products
    @GET("fetch_products.php")
    suspend fun fetchProducts(): ApiResponseProduct
    // api read pet
    @GET("fetch_pet.php")
    suspend fun fetchPets(): ApiResponsePet
    // api add cart
    @FormUrlEncoded
    @POST("add_cart.php")
    suspend fun addcart(
        @Field("iduser") iduser: Int,
        @Field("idproduct") idproduct: Int,
    ): ApiResponseCart
    // api add cart
    @FormUrlEncoded
    @POST("update_cart.php")
    suspend fun updateQuantity(
        @Field("idcart") idcart: Int,
        @Field("quantity") quantity : Int,
    ): ApiResponseCart
    // api read cart
    @GET("fetch_cart.php")  // Đặt tên endpoint của bạn ở đây
    suspend fun fetchCart(
    ): ApiResponseCart

    @FormUrlEncoded
    @POST("delete_cart.php")
    suspend fun deletecart(
        @Field("idcart") idcart: Int
    ): ApiResponseCart

    // api wareHouse
    @FormUrlEncoded
    @POST("add_warehouse.php")
    suspend fun add_warehouse(
        @Field("iduser") iduser: Int,
        @Field ("idproduct") idproduct: Int,
        @Field("quantity") quantity: Int
    ):ApiResponseWarehouse
    // api read wareHouse
    @GET("fetch_wasehouse.php")
    suspend fun fetchwarehouse():ApiResponseWarehouse
}
object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.102/PetStoreService/"  // Thay địa chỉ server của bạn
    val instance: APIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(APIService::class.java)
    }
}