import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VkGroups {
    private VkApiClient apiClient;
    private UserActor userActor;

    public static VkGroups auth(int id, String token) {
        var example = new VkGroups();
        example.apiClient = new VkApiClient(HttpTransportClient.getInstance());
        example.userActor = new UserActor(id, token);
        return example;
    }

    public static JSONObject groupList() throws ApiException, ClientException {
        var user = VkGroups.auth(187124030, "someToken");
        var res = new JSONObject(user.apiClient
                .groups()
                .getMembers(user.userActor)
                .groupId("198188261")
                .count(1000)
                .fields(new Fields[]{Fields.PHOTO_MAX, Fields.CITY, Fields.BDATE})
                .executeAsString());
        return res;
    }

    public static ArrayList<Human> jsonDEARCHIVE(JSONObject jsonObject){
        ArrayList<Human> humanArrayList = new ArrayList<>();
        for (Object person : jsonObject.getJSONObject("response").getJSONArray("items")) {
            JSONObject box = (JSONObject) person;
            String city;
            String birthday;
            try {
                city = box.getJSONObject("city").getString("title");
            } catch (JSONException e) {
                city = "null";
            }
            try {
                birthday = box.getString("bdate");
            } catch (JSONException e) {
                birthday = "null";
            }
            humanArrayList.add(new Human(box.getString("last_name") + " " + box.getString("first_name")
                    , box.getString("photo_max"), birthday, box.getLong("id"), city));
        }
        return humanArrayList;
    }
}