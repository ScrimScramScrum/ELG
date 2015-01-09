package springmvc.ui;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MakeAdmin {
    @NotNull
    @Size(min=2)
    String makeAdminPw;

    public String getMakeAdminPw() {
        return makeAdminPw;
    }

    public void setMakeAdminPw(String makeAdminPw) {
        this.makeAdminPw = makeAdminPw;
    }
}
