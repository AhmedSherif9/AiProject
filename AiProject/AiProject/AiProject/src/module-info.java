module AiProject {
    requires junit;
    
    // Export the `tests` package (replace `tests` with the actual package name if different)
    exports tests;
    
    // Open the `tests` package specifically to `junit` for reflection
    opens tests to junit;
}
