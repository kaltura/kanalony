import kanalony.storage.logic.{UserActivityQuery, MinutelyQuery}

/**
 * Created by elad.benedict on 2/10/2016.
 */
trait MinutelyUserAccessQuery extends UserActivityQuery with MinutelyQuery
