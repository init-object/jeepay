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
package com.jeequan.jeepay.mch.ctrl.sysuser;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jeequan.jeepay.core.constants.ApiCodeEnum;
import com.jeequan.jeepay.core.entity.SysUser;
import com.jeequan.jeepay.core.exception.BizException;
import com.jeequan.jeepay.mch.service.AuthService;
import com.jeequan.jeepay.mch.ctrl.CommonCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jeequan.jeepay.core.entity.SysUserRoleRela;
import com.jeequan.jeepay.service.impl.SysUserRoleRelaService;
import com.jeequan.jeepay.service.impl.SysUserService;
import com.jeequan.jeepay.core.model.ApiRes;

import java.util.Arrays;
import java.util.List;

/**
 * 用户角色管理类
 *
 * @author terrfly
 * @modify zhuxiao
 * @site https://www.jeepay.vip
 * @date 2021-04-27 15:50
 */
@RestController
@RequestMapping("api/sysUserRoleRelas")
public class SysUserRoleRelaController extends CommonCtrl {

	@Autowired private SysUserRoleRelaService sysUserRoleRelaService;
	@Autowired private SysUserService sysUserService;
	@Autowired private AuthService authService;

	/** list */
	@PreAuthorize("hasAuthority( 'ENT_UR_USER_UPD_ROLE' )")
	@RequestMapping(value="", method = RequestMethod.GET)
	public ApiRes list() {

		SysUserRoleRela queryObject = getObject(SysUserRoleRela.class);

		LambdaQueryWrapper<SysUserRoleRela> condition = SysUserRoleRela.gw();

		if(queryObject.getUserId() != null){
			condition.eq(SysUserRoleRela::getUserId, queryObject.getUserId());
		}

		IPage<SysUserRoleRela> pages = sysUserRoleRelaService.page(getIPage(true), condition);

		return ApiRes.page(pages);
	}

	/** 重置用户角色关联信息 */
	@PreAuthorize("hasAuthority( 'ENT_UR_USER_UPD_ROLE' )")
	@RequestMapping(value="relas/{sysUserId}", method = RequestMethod.POST)
	public ApiRes relas(@PathVariable("sysUserId") Long sysUserId) {
		SysUser dbRecord = sysUserService.getOne(SysUser.gw().eq(SysUser::getSysUserId, sysUserId).eq(SysUser::getBelongInfoId, getCurrentMchNo()));
		if (dbRecord == null) throw new BizException(ApiCodeEnum.SYS_OPERATION_FAIL_SELETE);

		List<String> roleIdList = JSONArray.parseArray(getValStringRequired("roleIdListStr"), String.class);

		sysUserService.saveUserRole(sysUserId, roleIdList);

		authService.refAuthentication(Arrays.asList(sysUserId));

		return ApiRes.ok();
	}


}
