// Axios
import axios from "axios";

// Language
class OtherLanguageServices {

    // language Flag button
    // accept-language= tr
    // accept-language= en
    headerLanguageServices(language) {
        axios.defaults.headers['accept-language'] = language;
    }

}//end class

// Değişken ataması yaparak dışa aktar
const otherLanguageServices = new OtherLanguageServices();
export default otherLanguageServices;