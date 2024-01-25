package metrics.counters.import_counter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PythonImportCounterMetric_ {
    @Test
    public void countImportsTest() {
        String code = "from transformers import pipeline\n" +
                "import torch\n" +
                "import pandas as pd\n" +
                "import numpy as np\n" +
                "import torchvision";

        int expectedImports = 5;
        PythonImportCounterMetric pythonImportCounterMetric = new PythonImportCounterMetric();
        int importCounter = pythonImportCounterMetric.countPythonImports(code);
        assertEquals(expectedImports, importCounter);
    }
}
