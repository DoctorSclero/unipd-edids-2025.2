javadoc -Xdoclint:none -d docs -cp .:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar \
-tag test.design:a:"Design" \
-tag test.description:a:"Description" \
-tag test.precondition:a:"Preconditions" \
-tag test.postcondition:a:"Postconditions" \
-tag test.libraries:a:"Libraries" \
-tag test.expectedresults:a:"Expected Results" **/*.java