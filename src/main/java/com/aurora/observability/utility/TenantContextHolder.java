package com.aurora.observability.utility;

import com.aurora.observability.exception.TenantNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@NoArgsConstructor
public final class TenantContextHolder {
    private static final ThreadLocal<String> tenantKey = new ThreadLocal<>();
    private static final String TENANT_NOT_FOUND_MESSAGE = "No Tenant Found in the Current Context";

    public static void setTenantKey(String tenantId) {
        Assert.hasText(tenantId, "Tenant can be empty!");
        tenantKey.set(tenantId);
    }

    public static String getTenantKey () throws TenantNotFoundException {
        if (StringUtils.hasText(tenantKey.get())) {
            throw new TenantNotFoundException(TENANT_NOT_FOUND_MESSAGE);
        }

        return tenantKey.get();
    }

    public static void resetTenant () {
        tenantKey.remove();
    }
}
