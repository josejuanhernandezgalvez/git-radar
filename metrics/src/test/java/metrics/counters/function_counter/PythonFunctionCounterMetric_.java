package metrics.counters.function_counter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PythonFunctionCounterMetric_ {
    @Test
    public void countPythonFunctions() {
        String code = "class DataAnalyzer:\n" +
                "    def __init__(self, df):\n" +
                "        self.df = df\n" +
                "        self.output = pd.DataFrame(columns=[\"Column\", \"Data Type\", \"Unique values\", \"NaN\"])\n" +
                "\n" +
                "    def _get_data_types(self, column):\n" +
                "        return self.df[column].dtypes\n" +
                "    \n" +
                "    def _get_unique_values(self,column):\n" +
                "        return len(self.df[column].unique())\n" +
                "    \n" +
                "    def _get_nan_proportion(self, column):\n" +
                "        return self.df[column].isna().sum() / self.df[column].shape[0]\n" +
                "    \n" +
                "    def analyze_data(self):\n" +
                "        for column in self.df.columns:\n" +
                "            data_types = self._get_data_types(column)\n" +
                "            unique_values = self._get_unique_values(column)\n" +
                "            nan_proportion = self._get_nan_proportion(column)\n" +
                "            \n" +
                "            data = pd.DataFrame(np.array([column, data_types, unique_values, nan_proportion]).reshape(1, 4),\n" +
                "                                columns=[\"Column\", \"Data Type\", \"Unique values\", \"NaN\"])\n" +
                "            \n" +
                "            self.output = pd.concat([self.output, data])\n" +
                "\n" +
                "        return self.output";

        int expectedFunctions = 5;
        PythonFunctionCounterMetric pythonFunctionCounterMetric = new PythonFunctionCounterMetric();
        int functions = pythonFunctionCounterMetric.countFunctions(code);
        assertEquals(expectedFunctions, functions);
    }
}
