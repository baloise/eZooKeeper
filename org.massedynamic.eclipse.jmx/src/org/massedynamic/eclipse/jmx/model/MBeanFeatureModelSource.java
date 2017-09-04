/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.jmx.model;

import org.massedynamic.eclipse.core.model.DataModelSourceException;
import org.massedynamic.eclipse.jmx.data.JmxConnection;
import org.massedynamic.eclipse.jmx.data.MBean;
import org.massedynamic.eclipse.jmx.data.MBeanFeature;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public abstract class MBeanFeatureModelSource<M extends MBeanFeatureModel<M, D>, D extends MBeanFeature<?>> extends
        AbstractJmxModelSource<M, String, D> {

    private final MBeanModel _MBeanModel;

    public MBeanFeatureModelSource(MBeanModel mbeanModel, JmxConnectionModel jmxConnectionModel) {
        super(jmxConnectionModel);
        _MBeanModel = mbeanModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.massedynamic.eclipse.zookeeper.model.DataModelSource#getData(java.lang.Object)
     */
    @Override
    public final D getData(String featureName) throws DataModelSourceException {
        MBean mbean = getMBean();
        JmxConnection jmxConnection = getJmxConnection();
        return getMBeanFeature(jmxConnection, mbean, featureName);
    }

    /**
     * Returns the mBeanModel.
     * 
     * @return The mBeanModel
     */
    public MBeanModel getMBeanModel() {
        return _MBeanModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.massedynamic.eclipse.zookeeper.model.AbstractDataModelSource#isGetKeysSupported()
     */
    @Override
    public boolean isGetKeysSupported() {
        return true;
    }

    /**
     * TODO: Comment.
     * 
     * @return
     */
    protected MBean getMBean() {
        return _MBeanModel.getData();
    }

    /**
     * TODO: Comment.
     * 
     * @param jmxConnection
     * @param mbean
     * @param featureName
     * @return
     */
    protected abstract D getMBeanFeature(JmxConnection jmxConnection, MBean mbean, String featureName);

}
