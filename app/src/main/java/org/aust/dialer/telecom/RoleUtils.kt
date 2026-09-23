package org.aust.dialer.telecom

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent

/** Default phone-app role helpers. The system UI grants the role; the app never bypasses it. */
object RoleUtils {
    private fun manager(context: Context): RoleManager? = context.getSystemService(RoleManager::class.java)

    fun isDefaultDialer(context: Context): Boolean {
        val rm = manager(context) ?: return false
        return try {
            rm.isRoleAvailable(RoleManager.ROLE_DIALER) && rm.isRoleHeld(RoleManager.ROLE_DIALER)
        } catch (e: Exception) {
            false
        }
    }

    /** The system role-request dialog, or null when the device does not offer the dialer role. */
    fun requestIntent(context: Context): Intent? {
        val rm = manager(context) ?: return null
        return try {
            if (rm.isRoleAvailable(RoleManager.ROLE_DIALER)) rm.createRequestRoleIntent(RoleManager.ROLE_DIALER) else null
        } catch (e: Exception) {
            null
        }
    }
}
