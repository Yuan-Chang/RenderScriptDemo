#pragma version(1)
#pragma rs java_package_name(yc.com.renderscriptdemo)

struct MyDataIn {
        int a;
        int b;
};

void root(const struct MyDataIn *m_in, int *m_out) {

     *m_out = m_in->a + m_in->b;

    rsDebug("RS a=", (int) m_in->a);
    rsDebug("RS b=", (int) m_in->b);
}