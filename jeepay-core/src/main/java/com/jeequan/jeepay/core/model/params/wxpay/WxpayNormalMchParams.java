/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jeequan.jeepay.core.model.params.wxpay;

import com.jeequan.jeepay.core.model.params.NormalMchParams;
import lombok.Data;

/*
 * 微信官方支付 配置参数
 *
 * @author zhuxiao
 * @site https://www.jeepay.vip
 * @date 2021/6/8 18:02
 */
@Data
public class WxpayNormalMchParams extends NormalMchParams {

    /** 应用App ID */
    private String appId;

    /** 应用AppSecret */
    private String appSecret;

    /** 微信支付商户号 */
    private String mchId;

    /** oauth2地址 */
    private String oauth2Url;

    /** API密钥 */
    private String key;

    /** 微信支付API版本 **/
    private String apiVersion;

    /** API V3秘钥 **/
    private String apiV3Key;

    /** 序列号 **/
    private String serialNo;

    /** API证书(.p12格式)**/
    private String cert;

    /** 私钥文件(.pem格式) **/
    private String apiClientKey;

}
