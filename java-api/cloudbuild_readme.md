# Cloud Build
This README documents the manual process used to configure Cloud Build to build and deploy an application to app engine.

## Requirements
- There is a billing method associated with the account.
- A GCP project to house the deployed application has been created.

## Steps
Go to __Cloud Build__ and add triggers for main and feature builds
   1. Go to the 'Triggers' section and click 'Connect Repository'
   2. Leave 'Select source' as default selection (GitHub (Cloud Build GitHub App) and authenticate, select the target repository from the dropdown menu and click 'Connect'
   3. Click 'Create a Trigger'
   4. Give the trigger an appropriate name (master-build/feature-build)
   5. For main builds, leave all selections as default and click 'Create'. cloudbuild.yaml should be automatically detected to provide the configuration.
   6. For feature builds:
      1. Select 'Cloud Build configuration file (yaml or json)' under the __Configuration__ section and add 'cloudbuild-feature.yaml' to the 'Cloud Build configuration file location' entry.
      2. Put __^feature/.*$__ as the 'Branch' input to detect and build on any commits to any feature branch
      3. OR To build more selectively, select 'Push new tag' as the event and add __^build-.*$__ as the "Branch" input. Builds can be triggered by tagging and pushing commits i.e:
         ```
         git tag build-<tag-suffix> && git push origin build-<tag-suffix>
         ```
    7. Click 'Create' to finish creating the trigger
    8. Repeat from step c onwards to add additional triggers.

## Testing correct setup
The following steps verify that the application has been deployed successfully.

1. Go to triggers and click 'run' next to each trigger, they should complete successfully.
2. When a trigger has been completed successfully, Go to __App Engine__ and click the most recent version in the 'Versions' section to view the running application.<br>
   Note: Endpoints can be checked by adding them to the URL to ensure that they are functioning as they are expected.
