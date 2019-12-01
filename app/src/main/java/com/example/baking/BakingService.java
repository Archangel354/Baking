package com.example.baking;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BakingService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FETCH_INGREDIENTS = "com.example.baking.action.update_recipe";

    private static final String ACTION_BAZ = "com.example.baking.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_RECIPE = "com.example.baking.extra.RECIPE";
    private static final String EXTRA_INGREDIENTS = "com.example.baking.extra.INGREDIENTS";

    public BakingService() {
        super("BakingService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionUpdateRecipe(Context context, String recipe, String ingredients) {
        Intent intent = new Intent(context, BakingService.class);
        intent.setAction(ACTION_FETCH_INGREDIENTS);
        intent.putExtra(EXTRA_RECIPE, recipe);
        intent.putExtra(EXTRA_INGREDIENTS, ingredients);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, BakingService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_RECIPE, param1);
        intent.putExtra(EXTRA_INGREDIENTS, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_INGREDIENTS.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_RECIPE);
                final String param2 = intent.getStringExtra(EXTRA_INGREDIENTS);
                handleActionUpdateRecipe(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_RECIPE);
                final String param2 = intent.getStringExtra(EXTRA_INGREDIENTS);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpdateRecipe(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
